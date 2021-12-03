package edu.iastate.myclub.controllers.user;

import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.iastate.myclub.models.*;
import edu.iastate.myclub.services.*;
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
	
	@GetMapping("/get")
	public ResponseEntity<User> getUser(@RequestHeader HttpHeaders headers, @RequestParam("name") String name ){
		return new ResponseEntity<User>(userService.getUserByfirstName(name), HttpStatus.OK);
	}
}
