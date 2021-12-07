package edu.iastate.myclub.repos.event;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.iastate.myclub.models.event.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

	Event findByTitleAndClubId(String title, int clubId);

	List<Event> findAllByDateContainingAndClubId(String date, int clubId);

	List<Event> findAllByDateContaining(String date);
	
}
