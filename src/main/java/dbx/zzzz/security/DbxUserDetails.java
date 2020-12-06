package dbx.zzzz.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import dbx.zzzz.model.Role;
import dbx.zzzz.model.User;

public class DbxUserDetails implements UserDetails {
	private User user;
    private final Logger log = LoggerFactory.getLogger(DbxUserDetails.class);

    public DbxUserDetails(User user) {
	        this.user = user;
	    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> auth = new ArrayList<>();
		for (Role role : roles) {
			auth.add(new SimpleGrantedAuthority(role.getName()));
			log.info("role name=" + role.getName() + ".");
		}
		return auth;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}
