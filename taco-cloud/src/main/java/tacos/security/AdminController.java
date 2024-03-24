package tacos.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.User;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

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

}