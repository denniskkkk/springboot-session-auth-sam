package dbx.zzzz.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    private String[] res = new String[]{
           "/home/**","/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**", "/images/**", "/static/**"
            , "/login", "/logout", "/wsapi"
    };
	
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.inMemoryAuthentication ()
		.withUser ("user").password(passwordEncoder().encode("password")).roles("USER")
		.and()
		.withUser ("admin").password(passwordEncoder().encode("password")).roles("ADMIN","USER");
		*/
        auth.authenticationProvider(authenticationProvider());
        //auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
    @Bean
    public UserDetailsService userDetailsService() {
        return new DbxUserDetailsServiceImpl();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
        
		http.csrf().disable()  // must for REST cross site request forges
		//.cors().disable()
		.headers().frameOptions().disable().and()
		.authorizeRequests()
		.antMatchers("/**").permitAll()  // test only
		//.antMatchers("/").permitAll()  // test only		
        .antMatchers(res).permitAll()  		
        //.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()        		
        .antMatchers(HttpMethod.POST, "/data/list/**").hasAnyAuthority( "ADMIN")
        .antMatchers(HttpMethod.PUT, "/data/list/**").hasAnyAuthority( "ADMIN")
        .antMatchers(HttpMethod.DELETE, "/data/list/**").hasAnyAuthority( "ADMIN")
        .antMatchers(HttpMethod.GET, "/data/list/**").hasAnyAuthority("USER", "ADMIN")
        .antMatchers(HttpMethod.OPTIONS, "/data/list/**").hasAnyAuthority("ADMIN") // must have for REST 
        .antMatchers("/guest/**").hasAnyAuthority("GUEST")
        .antMatchers("/admin/**").hasAnyAuthority("ADMIN") //.access("hasRole('ADMIN')")
        .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")   //access("hasRole('USER') or hasRole('ADMIN')")
        .antMatchers("/websocket").hasAnyAuthority("ADMIN")   // websocket access
        .anyRequest().authenticated()
        .and().formLogin().permitAll().defaultSuccessUrl("/menu/")
        .and().logout().permitAll().logoutSuccessUrl("/login")
        .and().exceptionHandling().accessDeniedPage("/403")		// need path url /error
        ;   
		//.anyRequest().authenticated().and().formLogin();
	    //.loginPage("/login").permitAll().and().logout().permitAll()
		//.and().exceptionHandling().accessDeniedPage("/403");		// need template /403.html
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
