package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 为了能够访问h2的界面，必须设置2个配置：https://www.jianshu.com/p/925d5aece6dc
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();
		return http.authorizeRequests().requestMatchers("/design", "/orders").access("hasRole('USER')")
				.requestMatchers("/", "/**").access("permitAll()")

				.and().formLogin().loginPage("/login")

				.and().csrf().disable().build();
	}

}