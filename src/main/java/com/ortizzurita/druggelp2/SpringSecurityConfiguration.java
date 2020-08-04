package com.ortizzurita.druggelp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ortizzurita.druggelp2.models.services.UsuarioService;
import com.ortizzurita.druggelp2.security.LoginSuccessHandler;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private LoginSuccessHandler handler;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Autowired //Authetication
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{	
		build.userDetailsService(service).passwordEncoder(encoder());		
	}
	
	@Override //Autorization
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/assets/**","/Doc/**","/js/**","/shop").permitAll()
			.antMatchers("/usuario/**").anonymous()
			.antMatchers("/administrador/**").hasAnyRole("USER")
			.antMatchers("/farmacia/**").hasAnyRole("USER")
			.antMatchers("/farmaco/**").hasAnyRole("USER")
			.antMatchers("/h2-console/**").hasAnyRole("USER")
			.anyRequest().authenticated()
			.and().formLogin().successHandler(handler).loginPage("/login").permitAll()			
			.and().logout().permitAll()			
			.and().exceptionHandling().accessDeniedPage("/login")
			.and()
				.csrf().ignoringAntMatchers("/h2-console/**")
			.and()
				.headers().frameOptions().sameOrigin();
	}
	
}
