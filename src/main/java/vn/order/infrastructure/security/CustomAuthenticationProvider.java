package vn.order.infrastructure.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private AuthUserConfig userConfig;
	
	private PasswordEncoder passEncoder = new BCryptPasswordEncoder();

	@Override
	public Authentication authenticate(Authentication authentication) {
		
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		AuthProperties.User user = userConfig.getUserByUserName(name);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid User");
		}
		
		if(StringUtils.isEmpty(password)) {
			throw new BadCredentialsException("Password is required");
		}

		boolean isPasswordMatched = passEncoder.matches(password, user.getPassword());
		if(!isPasswordMatched) {
			throw new BadCredentialsException("Wrong password");
		}
		
		final List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_APP"));
		
		final UserDetails principal = new CustomUserDetails(user.getUsername(),
        		user.getPassword(),
        		grantedAuths,
        		user.getChannel());
		
		final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
