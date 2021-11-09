package edu.iastate.myclub.controllers.club;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.iastate.myclub.models.club.ClubDto;
import edu.iastate.myclub.services.club.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping("/scheduled")
	public ResponseEntity<List<ClubDto>> getClubEventsByMonth(@RequestHeader HttpHeaders headers, @RequestParam("club") String club, @RequestParam("year") String year, @RequestParam("month") String month)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<List<ClubDto>>(eventService.findClubEventsByMonthAndYear(club, year, month), HttpStatus.OK);
		//}
		//return new ResponseEntity<List<ClubDto>>(new ArrayList<ClubDto>(), HttpStatus.FORBIDDEN);
	}
}
