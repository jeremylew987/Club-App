package edu.iastate.myclub.repos.user;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.iastate.myclub.models.user.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer>{
	List<User> findAllUsersByName(String name, Pageable pageable);
}
