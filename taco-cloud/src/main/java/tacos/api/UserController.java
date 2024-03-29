package tacos.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.CommonConfig;
import tacos.User;
import tacos.UserVO;
import tacos.data.UserRepository;
import tacos.security.RegistrationForm;

@RestController
@RequestMapping(path = "/api/users", produces = "application/json")
@CrossOrigin(origins = "https://www.toolscat.com/")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CommonConfig commonConfig;

	/**
	 * 查询所有用户：http://localhost:8080/api/users?recent
	 * @return
	 */
	@GetMapping(params = "recent")
	public Iterable<User> recentUsers() {
		return userRepo.findAll();
	}

	/**
	 * 查询单个用户：http://localhost:8080/api/users/1
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<User> userById(@PathVariable("id") Long id) {
		return userRepo.findById(id);
	}
	
	/**
	 * 查询单个用户：http://localhost:8080/api/users/other/1
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/other/{id}")
	public ResponseEntity<User> userByIdEntity(@PathVariable("id") Long id) {
		Optional<User> optUser = userRepo.findById(id);
		if (optUser.isPresent()) {
			return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 新增用户：
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User postTaco(@RequestBody RegistrationForm form) {
		return userRepo.save(form.toUser(passwordEncoder));
	}
	
	/**
	 * 测试rest服务：
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/testRest/{id}")
	public ResponseEntity<UserVO> testRest(@PathVariable("id") Long id) {
		UserVO user = commonConfig.restTemplate().getForObject("http://localhost:8080/api/users/{id}", UserVO.class, id);
		System.out.println(user);
		return new ResponseEntity<>(user, HttpStatus.OK); 
	}

}
