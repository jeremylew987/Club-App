package edu.iastate.myclub.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.iastate.myclub.services.user.UserService;
import edu.iastate.myclub.models.user.User;

@RestController
@RequestMapping(path="/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> createUser(@RequestHeader HttpHeaders headers, @RequestBody User user){
		return new ResponseEntity<Boolean>(userService.createUser(user),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> loginAcceptable(@RequestHeader HttpHeaders header, @RequestBody User user){
		return new ResponseEntity<Boolean>(userService.passphraseMatch(user),HttpStatus.OK);
	}
	
	@GetMapping("/getByName")
	public ResponseEntity<User> getUser(@RequestHeader HttpHeaders headers, @RequestParam("name") String name ){
		return new ResponseEntity<User>(userService.getUserByfirstName(name), HttpStatus.OK);
	}
	
	@GetMapping("/getByUsername")
	public ResponseEntity<User> getUserByUsername(@RequestHeader HttpHeaders headers, @RequestParam("username") String username ){
		return new ResponseEntity<User>(userService.getUserByusername(username), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAll(@RequestHeader HttpHeaders headers){
		return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
	}
}
