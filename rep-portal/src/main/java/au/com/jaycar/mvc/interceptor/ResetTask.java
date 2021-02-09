package au.com.jaycar.mvc.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ResetTask extends TimerTask {
	
	private static final Logger logger = LoggerFactory.getLogger("ResetTask");
	
    private Map<String, Integer> redisTemplate = new HashMap<String,Integer>(); 
    
    public Map<String, Integer> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(Map<String, Integer> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void run() {
		Date now = new Date();
    	redisTemplate.clear();
    	logger.info("clear count at " + now.toLocaleString());
    	
    }
}
