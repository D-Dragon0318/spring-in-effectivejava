package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页控制器类
 */
@Controller
public class HomeController {
	
	/**
	 * 接收并处理针对根路径"/"的请求
	 * @return
	 */
	@GetMapping("/")
	public String home() {
		//返回视图名
		return "home";
	}
	
}
