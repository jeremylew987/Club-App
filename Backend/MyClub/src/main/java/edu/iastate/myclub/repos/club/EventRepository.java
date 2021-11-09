package edu.iastate.myclub.repos.club;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.iastate.myclub.models.event.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

}
