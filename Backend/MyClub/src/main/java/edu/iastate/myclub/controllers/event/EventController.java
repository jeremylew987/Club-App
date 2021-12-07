package edu.iastate.myclub.controllers.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.iastate.myclub.models.event.EventDto;
import edu.iastate.myclub.services.event.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventService eventService;
	
	public EventController() {}
	public EventController(EventService eventService)
	{
		this.eventService = eventService;
	}
	
	@GetMapping("/scheduled")
	public ResponseEntity<List<EventDto>> getClubEventsByMonthAndYear(@RequestHeader HttpHeaders headers, @RequestParam("club") String clubName, @RequestParam("month") String month, @RequestParam("year") String year)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<List<EventDto>>(eventService.findClubEventsByMonthAndYear(clubName, month + "/" + year), HttpStatus.OK);
		//}
		//return new ResponseEntity<List<ClubDto>>(new ArrayList<ClubDto>(), HttpStatus.FORBIDDEN);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> addEvent(@RequestHeader HttpHeaders headers, @RequestBody EventDto event)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<Boolean>(eventService.addEvent(event), HttpStatus.OK);
		//}
		//return new ResponseEntity<Boolean>(new ArrayList<ClubDto>(), HttpStatus.FORBIDDEN);
	}
	
	@PostMapping("/modify")
	public ResponseEntity<Boolean> modifyEvent(@RequestHeader HttpHeaders headers, @RequestBody EventDto event)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<Boolean>(eventService.modifyEvent(event), HttpStatus.OK);
		//}
		//return new ResponseEntity<Boolean>(new ArrayList<ClubDto>(), HttpStatus.FORBIDDEN);
	}
}


























