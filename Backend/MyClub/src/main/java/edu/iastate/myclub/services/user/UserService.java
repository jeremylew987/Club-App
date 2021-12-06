package edu.iastate.myclub.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
	
	public User getUserByfirstName(String firstName) {
		System.out.println(firstName);
		int page = 1;
		int size = 10;
		Pageable pageAndSortByName = PageRequest.of(page, size);
		java.util.List<User> userList = userRepository.findAllUsersByfirstName(firstName,pageAndSortByName);
		System.out.println(userList.size() + " " );
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getFirstName().equals(firstName)) {
				return userList.get(i);
			}
		}
		System.out.println("hi\n");
		return null;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
}
