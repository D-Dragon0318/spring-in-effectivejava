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
	 * 过滤链条：任何请求都要走到这里执行过滤操作
	 * 
	 * 为了能够访问h2的界面，必须设置2个配置：https://www.jianshu.com/p/925d5aece6dc
	 * 
	 * 注意：用无痕浏览器哈，否则登录了一次，只要不关闭浏览器权限就会一直存在
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

				//创建自定义的登录页
				.and().formLogin().loginPage("/login")
				
				//开发环境禁用了csrf，因为会影响h2后台界面的访问
				.and().csrf().disable().build();
	}

}