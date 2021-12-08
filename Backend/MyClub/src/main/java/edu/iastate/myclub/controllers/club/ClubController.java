package edu.iastate.myclub.controllers.club;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.iastate.myclub.models.club.ClubBasicDto;
import edu.iastate.myclub.models.club.ClubDto;
import edu.iastate.myclub.models.club.ClubNotificationDto;
import edu.iastate.myclub.models.user.User;
import edu.iastate.myclub.services.club.ClubService;
import edu.iastate.myclub.services.user.UserService;

@RestController
@RequestMapping("/club")
public class ClubController {

	@Autowired
	private ClubService clubService;
	
	@Autowired
	private UserService userService;
	
	public ClubController() {}
	public ClubController(ClubService clubService)
	{
		this.clubService = clubService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> createClub(@RequestHeader HttpHeaders headers, @RequestBody ClubDto club)
	{
		return new ResponseEntity<Boolean>(clubService.createClub(club), HttpStatus.OK);
	}
	
	@PostMapping("/modify")
	public ResponseEntity<Boolean> modifyClub(@RequestBody ClubDto club)
	{
		return new ResponseEntity<Boolean>(clubService.modifyClub(club), HttpStatus.OK);
	}
	
	@GetMapping("/joined")
	public ResponseEntity<List<ClubBasicDto>> getJoinedClubs(@RequestHeader HttpHeaders headers, @RequestParam("username") String username)
	{
		return new ResponseEntity<List<ClubBasicDto>>(clubService.getJoinedClubs(username), HttpStatus.OK);
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> joinClub(@RequestHeader HttpHeaders headers, @RequestParam("club") String clubName)
	{
		if(userService.passphraseMatch(new User("","",headers.get("Authorization").get(0).split(":")[1],headers.get("Authorization").get(0).split(":")[0])))
			return new ResponseEntity<String>(clubService.joinClub(headers.get("Authorization").get(0).split(":")[0], clubName), HttpStatus.OK);
		
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/leave")
	public ResponseEntity<String> leaveClub(@RequestHeader HttpHeaders headers, @RequestParam("club") String clubName)
	{
		if(userService.passphraseMatch(new User("","",headers.get("Authorization").get(0).split(":")[1],headers.get("Authorization").get(0).split(":")[0])))
			return new ResponseEntity<String>(clubService.leaveClub(headers.get("Authorization").get(0).split(":")[0], clubName), HttpStatus.OK);
	
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ClubBasicDto>> getClubsBySearch(@RequestHeader HttpHeaders headers, @RequestParam("phrase") String phrase, @RequestParam("page") int page)
	{
		return new ResponseEntity<List<ClubBasicDto>>(clubService.findClubs(phrase, page, 5), HttpStatus.OK);
	}
	
	@GetMapping("/search/narrowed")
	public ResponseEntity<ClubDto> getClubsBySearch(@RequestHeader HttpHeaders headers, @RequestParam("club") String clubName)
	{
		return new ResponseEntity<ClubDto>(clubService.getClubInformation(clubName), HttpStatus.OK);
	}
	
	@GetMapping("/joined/notifications")
	public ResponseEntity<List<ClubNotificationDto>> getJoinedClubsNotifications(@RequestHeader HttpHeaders headers, @RequestParam("page") int page)
	{
		if(userService.passphraseMatch(new User("","",headers.get("Authorization").get(0).split(":")[1],headers.get("Authorization").get(0).split(":")[0])))
			return new ResponseEntity<List<ClubNotificationDto>>(clubService.getJoinedClubsNotifications(headers.get("Authorization").get(0).split(":")[0], page), HttpStatus.OK);
		
		return new ResponseEntity<List<ClubNotificationDto>>(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/notifications/add")
	public ResponseEntity<Boolean> addClubNotification(@RequestHeader HttpHeaders headers, @RequestBody ClubNotificationDto notification)
	{
		if(userService.passphraseMatch(new User("","",headers.get("Authorization").get(0).split(":")[1],headers.get("Authorization").get(0).split(":")[0])))
			return new ResponseEntity<Boolean>(clubService.addClubNotification(headers.get("Authorization").get(0).split(":")[0], notification), HttpStatus.OK);
	
		return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping(path="/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Boolean> uploadFile(@RequestParam("club") String clubName, @RequestParam("upload") MultipartFile file)
	{
		return new ResponseEntity<Boolean>(clubService.store(file, clubName), HttpStatus.OK);
	}
	
	@GetMapping(path="/logo/download")
	public ResponseEntity<Resource> serveImageFile(@RequestParam("club") String club)
	{
		Resource file = clubService.loadAsResource(club);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(file);
	}
}
