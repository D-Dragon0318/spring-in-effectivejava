package tacos.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.User;
import tacos.data.UserRepository;

@RestController
@RequestMapping(path = "/api/users", produces = "application/json")
//@CrossOrigin(origins = "http://tacocloud:8080")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping(params = "recent")
	public Iterable<User> recentUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<User> userById(@PathVariable("id") Long id) {
		return userRepo.findById(id);
	}

}
