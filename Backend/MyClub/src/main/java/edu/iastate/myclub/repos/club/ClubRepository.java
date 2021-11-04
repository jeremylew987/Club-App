package edu.iastate.myclub.repos.club;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.iastate.myclub.models.Club;

@Repository
public interface ClubRepository extends CrudRepository<Club, Integer> {

}
