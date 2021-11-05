package edu.iastate.myclub.repos.club;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.iastate.myclub.models.Club;

@Repository
public interface ClubRepository extends CrudRepository<Club, Integer> {

	//Find clubs whose name contain the specific name as a substring, ignoring case
	//Page<Club> findAll(PageRequest of, Sort by);
}