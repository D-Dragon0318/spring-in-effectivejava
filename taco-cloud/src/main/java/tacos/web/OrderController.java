package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}

	/**
	 * 处理创建订单请求
	 * 
	 * @param order
	 * @param sessionStatus
	 * @return
	 */
	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
		
		/**
		 * 表单校验
		 */
		if (errors.hasErrors()) {
			return "orderForm";
		}

		log.info("Order submitted: {}", order);
		// 清除会话
		sessionStatus.setComplete();

		return "redirect:/";
	}

}