package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(security -> security
				.requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasRole("MANAGER")
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.csrf(cf -> cf.disable()); // csrf 보고 비활성화(JavaSript 호출)
		http.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess",true)); // SpringBoot가 제공해주는 로그인 사용
		http.exceptionHandling(ex -> ex.accessDeniedPage("/accessDenied"));
		http.logout(logout->logout
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login"));

		
		return http.build();
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("manager")
		.password("{noop}abcd")
		.roles("MANAGER");
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("{noop}abcd")
		.roles("ADMIN");
	}
	
}
