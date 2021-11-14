package edu.iastate.myclub.repos.club;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.iastate.myclub.models.club.Club;

@Repository
public interface ClubRepository extends PagingAndSortingRepository<Club, Integer> {

	//Find clubs whose name contain the specific name as a substring, ignoring case
	List<Club> findAllByNameOrderByNameAsc(String name, Pageable pageable);
}