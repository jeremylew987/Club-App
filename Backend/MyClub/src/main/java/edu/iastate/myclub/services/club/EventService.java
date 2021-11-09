package edu.iastate.myclub.services.club;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iastate.myclub.models.club.ClubDto;
import edu.iastate.myclub.repos.club.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public List<ClubDto> findClubEventsByMonthAndYear(String club, String year, String month) {
		
		return null;
	}
	
	
}
