package com.yuansong.study.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**")
			.hasRole("ADMIN")
			.antMatchers("/user/**")
			.hasAnyRole("ADMIN","USER")
			.antMatchers("/db/**")
			.access("hasRole('ADMIN') and hasRole('DBA')")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginProcessingUrl("/login")
			.usernameParameter("name")
			.passwordParameter("passwd")
			.successHandler(this.getSuccessHandler())
			.failureHandler(this.getFailureHandler())
			.permitAll()
			.and()
			.csrf()
			.disable();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("root").password("123").roles("ADMIN","DBA")
			.and()
			.withUser("admin").password("123").roles("ADMIN","USER")
			.and()
			.withUser("sang").password("123").roles("USER");
	}
	
	private AuthenticationSuccessHandler getSuccessHandler() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
					Authentication auth) throws IOException, ServletException {
				Object principal = auth.getPrincipal();
				resp.setContentType("application/json;charset=utf-8");
				resp.setStatus(200);
				Map<String,Object> map = new HashMap<>();
				map.put("status", 200);
				map.put("msg", principal);
				PrintWriter out = resp.getWriter();
				ObjectMapper om = new ObjectMapper();
				out.write(om.writeValueAsString(map));
				out.flush();
				out.close();
			}
		};
	}
	
	private AuthenticationFailureHandler getFailureHandler() {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
					AuthenticationException e) throws IOException, ServletException {
				resp.setContentType("application/json;charset=utf-8");
				resp.setStatus(401);
				Map<String,Object> map = new HashMap<>();
				map.put("status", 401);
				if(e instanceof LockedException) {
					map.put("msg", "账户被锁定，登录失败");					
				} else if (e instanceof BadCredentialsException) {
					map.put("msg", "账户名或密码输入错误，登录失败");
				} else if (e instanceof DisabledException) {
					map.put("msg", "账户被禁用，登录失败");
				} else if (e instanceof AccountExpiredException) {
					map.put("msg", "账户已过期，登录失败");
				} else if (e instanceof CredentialsExpiredException) {
					map.put("msg", "密码已过期，登录失败");
				} else {
					map.put("msg", "登录失败");
				}
				
				PrintWriter out = resp.getWriter();
				ObjectMapper om = new ObjectMapper();
				out.write(om.writeValueAsString(map));
				out.flush();
				out.close();
			}
			
		};
	}
}
