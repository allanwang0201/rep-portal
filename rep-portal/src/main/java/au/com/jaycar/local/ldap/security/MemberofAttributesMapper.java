package au.com.jaycar.local.ldap.security;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.ldap.core.AttributesMapper;

public class MemberofAttributesMapper implements AttributesMapper {


	
	@Override
	public Object mapFromAttributes(javax.naming.directory.Attributes attrs) throws javax.naming.NamingException {
		List<String> memberof = new ArrayList();
		for (Enumeration vals = attrs.get("memberOf").getAll(); vals.hasMoreElements();) {
			memberof.add((String) vals.nextElement());
		}
		return memberof;
	}
}
