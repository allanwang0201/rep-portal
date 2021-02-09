package au.com.jaycar.mvc.interceptor;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.springframework.web.method.HandlerMethod;

@Component
public class RequestLimitInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger("RequestLimitInterceptor");

	private AccessLimitTimer timer;

	@Resource(name = "repProperties")
	private Properties repProperties;

	@PostConstruct
	public void initIt() throws Exception {
		long period = Integer.parseInt(repProperties.getProperty("access.limitation.period"));
		timer = new AccessLimitTimer(period);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		if (request.getRequestURI().contains("."))// if it is js css html files,ignore interception
			return true;
		RequestLimit methodRequestLimit = ((HandlerMethod) handler).getMethodAnnotation(RequestLimit.class);
		RequestLimit classRequestLimit = ((HandlerMethod) handler).getBean().getClass()
				.getAnnotation(RequestLimit.class);
		boolean vcode = true;

		int count = Integer.parseInt(repProperties.getProperty("access.limitation.max")) * 4;// four request per product search

		if (methodRequestLimit != null) {
			vcode = validateCode(request, response, count);
		} else if (classRequestLimit != null) {
			vcode = validateCode(request, response, count);
		}
		return vcode;
	}

	/**
	 * frequence limitation
	 * 
	 * @param request
	 * @return
	 */
	private boolean validateCode(HttpServletRequest request, HttpServletResponse response, int maxSize) {

		if (request == null) {
			return false;// throw new RequestLimitException("Missing HttpServletRequest params in method");
		}

		String code = request.getParameter("params");

		String uri = request.getRequestURI();

		String product = uri.split("/")[uri.split("/").length - 1];

		boolean vcode = true;

		UserDetails userDetails = null;
		
		try {
			userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (java.lang.ClassCastException e) {
			request.getSession().setAttribute("expire", "seesion has expired, please login again");
			
			SecurityContextHolder.clearContext();
			return false;
		}

		request.getSession().setAttribute("expire", "");
		
		String ip = request.getRemoteAddr();
		String url = request.getRequestURL().toString();
		String key = "req_limit_".concat(userDetails.getUsername());
		Date time = new Date();

		if (uri.contains("mdm/products/code/")) {
			logger.info("access log: user " + userDetails.getUsername() + " search " + product + " from " + ip + " at " + time.toLocaleString());
		}
		Map<String, Integer> redisTemplate = timer.getTask().getRedisTemplate();

		if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0) {
			redisTemplate.put(key, 1);
		} else {
			redisTemplate.put(key, redisTemplate.get(key) + 1);
		}
		int count = redisTemplate.get(key);

		if (count > maxSize) {
			logger.info("User IP " + ip + " Access address " + url + " exceed limitation of" + maxSize);

			int max = Integer.parseInt(repProperties.getProperty("access.limitation.max"));
					
			request.getSession().setAttribute("limit", "Access exceed limitation: " + max + " request per day");
			return false;
		}

		request.getSession().setAttribute("limit", "");
		return vcode;
	}
}
