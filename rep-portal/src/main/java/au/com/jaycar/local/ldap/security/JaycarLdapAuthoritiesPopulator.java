package au.com.jaycar.local.ldap.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

public class JaycarLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

	@Autowired
	private LdapTemplate ldapTemplate;

	
	@Resource(name="repProperties")
	private Properties repProperties;

	
	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData,
			String username) {
		
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		List<Object> result = getPersonGroupsByAccountName(username);
		
		List<String> members = null;
		
		if(!result.isEmpty())
			members = (List<String>)result.get(0);
		
		
		List<String> membersName = new ArrayList<String>();
		
		for(String member :members){
			String memberName = member.split(",")[0].split("=")[1];
			membersName.add(memberName);
		}
		
		String groups = repProperties.getProperty("access.allowed.group");
		
		
		if(groups == null){
			authorities.add(new SimpleGrantedAuthority("ROLE_REP"));
		}
		else if(membersName != null && membersName.size() > 0){
			for(String member : membersName){
				if(groups.contains(member)){
					authorities.add(new SimpleGrantedAuthority("ROLE_REP"));
				}
			}
			
		}
		
		
		return authorities;
	}

	public List<Object> getPersonGroupsByAccountName(String accountName) {
		
		EqualsFilter filter = new EqualsFilter("sAMAccountName", accountName);
		
		return ldapTemplate.search(query().where("objectclass").is("user").and("sAMAccountName").is(accountName),
				new MemberofAttributesMapper()
		);

	};

}
