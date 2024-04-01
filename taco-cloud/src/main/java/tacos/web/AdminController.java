package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.CommonConfig;
import tacos.User;
import tacos.UserVO;
import tacos.messaging.UserMessagingService;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CommonConfig commonConfig;
	
	@Autowired
	private UserMessagingService userMessagingService;

	/**
	 * 实现方法级别的安全
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/deleteOrders")
	public String deleteAllOrders() {
		return "redirect:/admin";
	}

	/**
	 * 测试获取用户信息
	 * 
	 * @return
	 */
	@GetMapping("/showUserInfo")
	public String showUserInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		log.info("Processing user: {}", user);
		return "redirect:/admin";
	}

	/**
	 * 测试bean中消费配置属性
	 * 
	 * @return
	 */
	@GetMapping("/showPageSize")
	public String showPageSize() {
		log.info("Processing pageSize: {}", commonConfig.getPageSize());
		return "home";
	}
	
	/**
	 * 测试发送消息：
	 * 
	 * @return
	 */
	@GetMapping("/testSendMessage")
	public String testSendMessage() {
		UserVO user = new UserVO();
		user.setUsername("code");
		userMessagingService.sendUser(user);
		log.info("Sent user: {}", user);

		return "home";
	}

}
