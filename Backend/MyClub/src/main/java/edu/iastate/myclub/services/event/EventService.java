package edu.iastate.myclub.services.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iastate.myclub.models.club.ClubBasicDto;
import edu.iastate.myclub.models.event.Event;
import edu.iastate.myclub.models.event.EventDto;
import edu.iastate.myclub.repos.event.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public List<EventDto> findClubEventsByMonthAndYear(String clubName, String date) {
		
		//TODO make more efficient
		return (List<EventDto>)eventRepository.findAllByDateContaining(date).stream().filter(e -> {return e.getTitle().contentEquals(clubName);});
	}
	
	//TODO finish when user type is added
	public List<Event> findClubEventsByMembership(String clubName, String date) {
		
		//TODO make more efficient
		return (List<Event>)eventRepository.findAllByDateContaining(date).stream().filter(e -> {return e.getTitle().contentEquals(clubName);});
	}

	public Boolean addEvent(EventDto event) {
		if(eventRepository.findByTitle(event.getTitle()) != null)
			return false;
		
		eventRepository.save(new Event(event));
		return true;
	}
	
	public Boolean modifyEvent(EventDto event) {
		Event e = eventRepository.findByTitle(event.getTitle());
		if(e == null)
			return false;
		
		e.copyFromEventDto(event);
		eventRepository.save(e);
		return true;
	}
}
