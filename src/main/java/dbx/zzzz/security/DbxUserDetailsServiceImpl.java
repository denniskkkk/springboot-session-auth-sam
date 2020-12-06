package dbx.zzzz.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dbx.zzzz.model.User;
import dbx.zzzz.repository.UserRepository;

public class DbxUserDetailsServiceImpl implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(DbxUserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userrepos;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userrepos.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException ("user not found");
		}
		log.info("user name=" + user.getUsername() + ".");
		return new DbxUserDetails (user);
	}

}
