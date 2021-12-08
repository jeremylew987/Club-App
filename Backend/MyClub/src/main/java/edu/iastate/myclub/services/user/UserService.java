package edu.iastate.myclub.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.iastate.myclub.models.user.User;
import edu.iastate.myclub.repos.user.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Boolean createUser(User user) {
		//TODO make it so no duplicate users
		if(userRepository.findByUsername(user.getUsername()) == null) {
			userRepository.save(user);
			return true;
		}
		if(userRepository.findByUsername(user.getUsername()).getUsername().equals(user.getUsername())) {
			return false;
		}
		userRepository.save(user);
		return true;
	}
	
	public Boolean passphraseMatch(User user) {
		if(userRepository.findByUsername(user.getUsername()) ==  null) {
			return false;
		}
		return userRepository.findByUsername(user.getUsername()).getPassphrase().contentEquals(user.getPassphrase());
	}
	
	public User getUserByfirstName(String firstName) {
		int page = 0;
		int size = 10;
		Pageable pageAndSortByName = PageRequest.of(page, size);
		List<User> userList = userRepository.findAllUsersByfirstName(firstName,pageAndSortByName);
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getFirstName().equals(firstName)) {
				return userList.get(i);
			}
		}
		return null;
	}
	
	public User getUserByusername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
}
