package edu.iastate.myclub.repos.club;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.iastate.myclub.models.ContactDetails;

@Repository
public interface ContactDetailsRepository extends CrudRepository<ContactDetails, Integer> {

	ContactDetails findByName(String name);

	ContactDetails findByNameAndClubId(String name, int id);

	List<ContactDetails> findAllByClubId(int id);
}
