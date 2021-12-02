package edu.iastate.myclub.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sun.tools.javac.util.List;

import edu.iastate.myclub.models.user.User;
import edu.iastate.myclub.repos.user.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Boolean createUser(edu.iastate.myclub.models.user.User user) {
		//TODO make it so no duplicate users
		userRepository.save(user);
		return true;
	}
	
	public User getUser(String name) {
//		List<User> userList = userRepository.findAll();
//		for(int i = 0; i < userList.size(); i++) {
//			if(userList.get(i).getName().equals(name)) {
//				return userList.get(i);
//			}
//		}
		return null;
	}
	
}
