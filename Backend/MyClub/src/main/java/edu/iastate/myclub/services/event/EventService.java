package edu.iastate.myclub.services.event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iastate.myclub.models.club.Club;
import edu.iastate.myclub.models.club.ClubBasicDto;
import edu.iastate.myclub.models.event.Event;
import edu.iastate.myclub.models.event.EventDto;
import edu.iastate.myclub.repos.club.ClubRepository;
import edu.iastate.myclub.repos.event.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ClubRepository clubRepository;

	public List<EventDto> findClubEventsByMonthAndYear(String clubName, String date) {
		Club c = clubRepository.findByName(clubName);
		if(c == null)
			return new ArrayList<>();
		
		//TODO make more efficient
		return (List<EventDto>)eventRepository.findAllByDateContainingAndClubId(date, c.getId())
				.stream().map(event -> {return new EventDto(event);}).collect(Collectors.toList());
	}
	
	//TODO finish when user type is added
	public List<Event> findClubEventsByMembership(String clubName, String date) {
		
		//TODO make more efficient
		return (List<Event>)eventRepository.findAllByDateContaining(date).stream().filter(e -> {return e.getTitle().contentEquals(clubName);});
	}

	public Boolean addEvent(EventDto event) {
		Club c = clubRepository.findByName(event.getClubName());
		if(c == null)
			return false;
		
		if(c == null || eventRepository.findByTitleAndClubId(event.getTitle(), c.getId()) != null)
			return false;
		
		eventRepository.save(new Event(event, c));
		return true;
	}
	
	public Boolean modifyEvent(EventDto event) {
		Club c = clubRepository.findByName(event.getClubName());
		if(c == null)
			return false;
		
		Event e = eventRepository.findByTitleAndClubId(event.getTitle(), c.getId());
		if(e == null)
			return false;
		
		e.copyFromEventDto(event);
		eventRepository.save(e);
		return true;
	}
}
