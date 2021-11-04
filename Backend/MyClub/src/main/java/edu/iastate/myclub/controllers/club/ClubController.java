package edu.iastate.myclub.controllers.club;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.iastate.myclub.models.Club;
import edu.iastate.myclub.models.ClubDto;
import edu.iastate.myclub.services.ClubService;

@RestController
@RequestMapping("/club")
public class ClubController {

	@Autowired
	private ClubService clubService;
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> createClub(@RequestHeader HttpHeaders headers, @RequestBody Club club)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<Boolean>(clubService.createClub(club), HttpStatus.OK);
		//}
		//return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	//TODO determine best way to determine which club is modified with respect to user who made the request
	@PostMapping("/modify")
	public ResponseEntity<Boolean> modifyClub(@RequestBody Club club)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<Boolean>(clubService.modifyClub(club), HttpStatus.OK);
		//}
		//return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping("/joined")
	public ResponseEntity<List<ClubDto>> getJoinedClubs(@RequestHeader HttpHeaders headers)
	{
		//if(has valid permissions)
		//{
		//return new ResponseEntity<boolean>(clubService.getJoinedClubs(<user name here, from headers>), HttpStatus.OK);
		//}
		return null;//new ResponseEntity<List<ClubDto>>(null, HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/search/{phrase}")
	public ResponseEntity<List<ClubDto>> getClubsBySearch(@RequestHeader HttpHeaders headers, @PathVariable String phrase)
	{
		//if(has valid permissions)
		//{
		//return new ResponseEntity<List<ClubDto>>(clubService.findClubs(phrase), HttpStatus.OK);
		//}
		return null;//new ResponseEntity<List<ClubDto>>(null, HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/joined/notifications")
	public ResponseEntity<List<ClubDto>> getJoinedClubsNotifications(@RequestHeader HttpHeaders headers)
	{
		//if(has valid permissions)
		//{
		//return new ResponseEntity<boolean>(clubService.getClubNotifications(user name here), HttpStatus.OK);
		//}
		return null;//new ResponseEntity<List<ClubDto>>(null, HttpStatus.FORBIDDEN);
	}
}
