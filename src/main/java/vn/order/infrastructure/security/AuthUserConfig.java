package vn.order.infrastructure.security;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUserConfig {
	
	private Map<String, AuthProperties.User> mapUser = new HashMap<String, AuthProperties.User>();
	
	@Autowired
	private AuthProperties authProperties;
	
	@PostConstruct
	public void init() {
		
		for(AuthProperties.User user : authProperties.getUsers()) {
			mapUser.put(user.getUsername(), user);
		}
	}
	
	public AuthProperties.User getUserByUserName(String userName) {
		
		if(!mapUser.containsKey(userName)) {
			return null;
		}
		return mapUser.get(userName);
	}
}