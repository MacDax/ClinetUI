package com.spring.boot;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/index").permitAll()
		.antMatchers("/login").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.failureUrl("/login?error=true")
		.defaultSuccessUrl("/index");
		
		
		 /*http.
         authorizeRequests()
             .antMatchers("/").permitAll()
             .antMatchers("/login").permitAll()
             .antMatchers("/registration").permitAll()
             .antMatchers("/**").hasAuthority("ADMIN").anyRequest()
             .authenticated().and().csrf().disable().formLogin()
             .loginPage("/login").failureUrl("/login?error=true")
             .defaultSuccessUrl("/home")
             .usernameParameter("email")
             .passwordParameter("password")
             .and().logout()
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/").and().exceptionHandling()
             .accessDeniedPage("/access-denied");*/
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/index");
	}
}
