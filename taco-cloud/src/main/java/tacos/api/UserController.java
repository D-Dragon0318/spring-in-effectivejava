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

import tacos.User;
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

	@GetMapping(params = "recent")
	public Iterable<User> recentUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<User> userById(@PathVariable("id") Long id) {
		return userRepo.findById(id);
	}

	@GetMapping("/other/{id}")
	public ResponseEntity<User> userByIdEntity(@PathVariable("id") Long id) {
		Optional<User> optUser = userRepo.findById(id);
		if (optUser.isPresent()) {
			return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User postTaco(@RequestBody RegistrationForm form) {
		return userRepo.save(form.toUser(passwordEncoder));
	}

}
