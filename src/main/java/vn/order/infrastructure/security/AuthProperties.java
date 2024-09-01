package vn.order.infrastructure.security;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("auth")
@Getter
@Setter
public class AuthProperties {
	
	private List<User> users;
	
	@Getter
	@Setter
	public static class User {
		private String username;
		private String password;
		private String channel;
	}
}
