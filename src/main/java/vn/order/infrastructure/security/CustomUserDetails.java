package vn.order.infrastructure.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9109993219816031156L;

	private Collection<? extends GrantedAuthority> authorities;

	private String channel;

	private String password;

	private String username;

	private Boolean enabled;

	private Boolean accountNonExpired;

	private Boolean accountNonLocked;

	private boolean credentialsNonExpired;

	public CustomUserDetails(String username,
			String password,
			Collection<? extends GrantedAuthority> authorities,
			String channel) {
	    this.enabled = true;
	    this.username = username;
	    this.password = password;
	    this.accountNonExpired = true;
	    this.accountNonLocked = true;
	    this.credentialsNonExpired = true;
	    this.authorities = authorities;
	    this.channel = channel;
	}
	
	public String getChannel() {
		return channel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
