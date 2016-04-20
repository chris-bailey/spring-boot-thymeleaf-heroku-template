package com.chrisbaileydeveloper.myapp.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Create two in-memory users (user & admin).
	 */
    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth
         	.inMemoryAuthentication()
         		.withUser("user").password("user").roles("USER").and()
         		.withUser("admin").password("admin").roles("ADMIN");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	            .antMatchers("/").permitAll()
	            .and()
            .formLogin()
	            .loginPage("/signin")
	            .permitAll()
	            .defaultSuccessUrl("/")
	            .and()
            .logout()
            	.logoutUrl("/logout")
                .logoutSuccessUrl("/?logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
    private static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    	
    	public GlobalSecurityConfiguration() {
    	}
    	
    }
}