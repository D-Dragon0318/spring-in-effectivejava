package tacos.web;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.inaction.lru.demo5.SafeLRUCacheWithGuava;
import ds.inaction.lru.demo5.SafeLRUCacheWithGuavaAsyncReloading;
import lombok.extern.slf4j.Slf4j;
import tacos.CommonConfig;
import tacos.User;
import tacos.UserVO;
import tacos.messaging.KafkaUserMessagingService;
import tacos.messaging.RabbitUserMessagingService;
import tacos.messaging.RabbitUserReceiver;
import tacos.simpleflow.FileWriterGateway;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CommonConfig commonConfig;
	
	@Autowired
	private RabbitUserMessagingService rabbitUserMessagingService;
	
	@Autowired
	private KafkaUserMessagingService kafkaUserMessagingService;
	
	@Autowired
	private RabbitUserReceiver rabbitUserReceiver;
	
	@Autowired
	private FileWriterGateway gateway;
	
	private	SafeLRUCacheWithGuavaAsyncReloading lruCache = new SafeLRUCacheWithGuavaAsyncReloading(3, 3);
	private	AtomicInteger atomicNum = new AtomicInteger();

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
		user.setUsername("代码悦读code");
		rabbitUserMessagingService.sendUser(user);
		log.info("Sent user: {}", user);

		return "home";
	}
	
	/**
	 * 测试拉取消息：
	 * 
	 * @return
	 */
	@GetMapping("/testReceiveMessage")
	public String testReceiveMessage() {
		UserVO user = rabbitUserReceiver.receiveUser();
		log.info("Receive user: {}", user);

		return "home";
	}
	
	/**
	 * 测试向Kafka中发送消息：
	 * 
	 * @return
	 */
	@GetMapping("/testSendMessageForKafka")
	public String testSendMessageForKafka() {
		UserVO user = new UserVO();
		user.setUsername("代码悦读code");
		kafkaUserMessagingService.sendUser(user);
		log.info("Sent user: {}", user);

		return "home";
	}
	
	/**
	 * 测试简单集成流：讲文件内容转换成大写
	 * @return
	 */
	@GetMapping("/testSimpleFlow")
	public String testSimpleFlow() {
		gateway.writeToFile("simple.txt", "Hello, Spring Integration!代码阅读");
		return "home";
	}
	
	/**
	 * 测试异步刷新功能：
	 * @return
	 */
	@GetMapping("/testPutCache")
	public String testPutCache() {
		lruCache.put(1L, "one");
		return "Success";
	}
	
	/**
	 * 测试异步刷新功能：
	 * @return
	 */
	@GetMapping("/testGetCache")
	public String testGetCache() {		
		String temp = String.valueOf(atomicNum.incrementAndGet());
		return temp + lruCache.get(1L);
	}

}
