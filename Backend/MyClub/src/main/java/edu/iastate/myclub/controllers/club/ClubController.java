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
import edu.iastate.myclub.services.club.ClubService;

@RestController
@RequestMapping("/club")
public class ClubController {

	@Autowired
	private ClubService clubService;
	
	public ClubController() {}
	public ClubController(ClubService clubService)
	{
		this.clubService = clubService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> createClub(@RequestHeader HttpHeaders headers, @RequestBody ClubDto club)
	{
		//if(has valid permissions)
		//{
		//System.out.println(club.toString());
		return new ResponseEntity<Boolean>(clubService.createClub(club), HttpStatus.OK);
		//}
		//return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	//TODO determine best way to determine which club is modified with respect to user who made the request
	@PostMapping("/modify")
	public ResponseEntity<Boolean> modifyClub(@RequestBody ClubDto club)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<Boolean>(clubService.modifyClub(club), HttpStatus.OK);
		//}
		//return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping("/joined") //TODO update api
	public ResponseEntity<List<ClubBasicDto>> getJoinedClubs(@RequestHeader HttpHeaders headers, @RequestParam("username") String username)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<List<ClubBasicDto>>(clubService.getJoinedClubs(username), HttpStatus.OK);
		//}
		//return null;//new ResponseEntity<List<ClubDto>>(null, HttpStatus.FORBIDDEN);
	}
	
	@PostMapping("/join") //TODO update api
	public ResponseEntity<String> joinClub(@RequestHeader HttpHeaders headers, @RequestParam("club") String clubName)
	{
		return new ResponseEntity<String>(clubService.joinClub(headers.get("Authorization").get(0).split(":")[0], clubName), HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ClubBasicDto>> getClubsBySearch(@RequestHeader HttpHeaders headers, @RequestParam("phrase") String phrase, @RequestParam("page") int page)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<List<ClubBasicDto>>(clubService.findClubs(phrase, page, 5), HttpStatus.OK);
		//}
		//return new ResponseEntity<List<ClubDto>>(new ArrayList<ClubDto>(), HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/search/narrowed")
	public ResponseEntity<ClubDto> getClubsBySearch(@RequestHeader HttpHeaders headers, @RequestParam("club") String clubName)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<ClubDto>(clubService.getClubInformation(clubName), HttpStatus.OK);
		//}
		//return new ResponseEntity<List<ClubDto>>(new ArrayList<ClubDto>(), HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/joined/notifications")
	public ResponseEntity<List<ClubNotificationDto>> getJoinedClubsNotifications(@RequestHeader HttpHeaders headers, @RequestParam("page") int page)
	{
		//if(has valid permissions)
		//{
		return new ResponseEntity<List<ClubNotificationDto>>(clubService.getJoinedClubsNotifications(headers.get("Authorization").get(0).split(":")[0], page), HttpStatus.OK);
		//}
		//return null;//new ResponseEntity<List<ClubDto>>(null, HttpStatus.FORBIDDEN);
	}
	
	@PostMapping("/notifications/add")
	public ResponseEntity<Boolean> addClubNotification(@RequestHeader HttpHeaders headers, @RequestBody ClubNotificationDto notification)
	{
		return new ResponseEntity<Boolean>(clubService.addClubNotification(headers.get("Authorization").get(0).split(":")[0], notification), HttpStatus.OK);
	}
	
	@PostMapping(path="/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Boolean> uploadFile(@RequestParam("club") String clubName, @RequestParam("upload") MultipartFile file)
	{		
//		if(has valid permissions && !file.isEmpty())
//		{

				return new ResponseEntity<Boolean>(clubService.store(file, clubName), HttpStatus.OK);
		//}
		
//		return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
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
