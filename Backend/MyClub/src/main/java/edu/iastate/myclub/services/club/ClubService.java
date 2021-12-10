package edu.iastate.myclub.services.club;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.iastate.myclub.models.club.Club;
import edu.iastate.myclub.models.club.ClubBasicDto;
import edu.iastate.myclub.models.club.ClubDto;
import edu.iastate.myclub.models.club.ClubLogo;
import edu.iastate.myclub.models.club.ClubNotification;
import edu.iastate.myclub.models.club.ClubNotificationDto;
import edu.iastate.myclub.models.user.User;
import edu.iastate.myclub.repos.club.ClubLogoRepository;
import edu.iastate.myclub.repos.club.ClubNotificationRepository;
import edu.iastate.myclub.repos.club.ClubRepository;
import edu.iastate.myclub.repos.club.ContactDetailsRepository;
import edu.iastate.myclub.repos.club.PositionRepository;
import edu.iastate.myclub.repos.user.UserRepository;

/**
 * Service providing club specific functionality and 
 * club related database operations
 * 
 * @author Graham Mobley
 *
 */
@Service
public class ClubService {

	@Autowired
	private ClubRepository clubRepository;
	
	@Autowired
	private ContactDetailsRepository contactDetailsRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired 
	private ClubLogoRepository clubLogoRepository;
	
	@Autowired
	private ClubNotificationRepository clubNotificationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ClubService() {}
	public ClubService(ClubRepository clubRepository, ContactDetailsRepository contactDetailsRepository,
			PositionRepository positionRepository, ClubLogoRepository clubLogoRepository,
			ClubNotificationRepository clubNotificationRepository, UserRepository userRepository)
	{
		this.clubRepository = clubRepository;
		this.contactDetailsRepository = contactDetailsRepository;
		this.positionRepository = positionRepository;
		this.clubLogoRepository = clubLogoRepository;
		this.clubNotificationRepository = clubNotificationRepository;
		this.userRepository = userRepository;
	}
	
	public boolean createClub(ClubDto club)
	{
		//TODO decide whether to do additional validation here and return false in case 
		//new club is bad
		if(clubRepository.findByName(club.getName()) != null)
			return false;
		Club newClub = clubRepository.save(new Club());
		clubRepository.save(newClub.copyFromClubDto(club, positionRepository, contactDetailsRepository, false));
		return true;
	}
	
	//TODO decide how to identify specific club entry in database to modify (possibly by name)
	public boolean modifyClub(ClubDto club)
	{
		//TODO decide whether to do additional validation here and return false in case 
		//new club is bad
		Club c = clubRepository.findByName(club.getName());
		if(c == null)
			return false;
		
		try { //when contacts are supplied an exception is thrown yet entry is still updated properly so will revisit if 
				//there is enough time
		clubRepository.save(c.copyFromClubDto(club, positionRepository, contactDetailsRepository, true));
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	public List<ClubBasicDto> getJoinedClubs(String name)
	{
		ArrayList<ClubBasicDto> clubs = new ArrayList<ClubBasicDto>();
		User user = userRepository.findByUsername(name);
		for(Club club: user.getJoinedClubs())
			clubs.add(new ClubBasicDto(club));
		return clubs;
	}
	
	public String joinClub(String username, String clubName) {
		User user = userRepository.findByUsername(username);
		if(user == null)
			return "User does not exist.";
		
		Club c = clubRepository.findByName(clubName);
		if(c == null)
			return "The provided club does not exist.";
		
		if(user.getJoinedClubs().contains(c))
			return "User has already joined this club.";
		
		user.getJoinedClubs().add(c);
		userRepository.save(user);
		
		c.setNumMembers(c.getNumMembers() + 1);
		clubRepository.save(c);
		return "SUCCESS";
	}
	
	public String leaveClub(String username, String clubName)
	{
		User user = userRepository.findByUsername(username);
		if(user == null)
			return "User does not exist.";
		
		Club c = clubRepository.findByName(clubName);
		if(c == null)
			return "The provided club does not exist.";
		
		if(!user.getJoinedClubs().contains(c))
			return "User isn't a member of this club.";
		
		user.getJoinedClubs().remove(c);
		userRepository.save(user);
		
		c.setNumMembers(c.getNumMembers() - 1);
		clubRepository.save(c);
		return "SUCCESS";
	}
	
	public List<ClubBasicDto> findClubs(String phrase, int page, int size)
	{
		Pageable pageAndSortByName = PageRequest.of(page, size);
		List<Club> clubs = clubRepository.findAllByNameContainingOrderByNameAsc(phrase, pageAndSortByName);
		return clubs.stream().map(club -> new ClubBasicDto(club)).collect(Collectors.toList());
	}
	
	public ClubDto getClubInformation(String clubName)
	{
		Club c = clubRepository.findByName(clubName);
		if(c == null)
			return null;
		
		ClubDto clubInformation = new ClubDto();
		clubInformation.copyFromClub(c);
		return clubInformation;
	}
	
	public List<ClubNotificationDto> getJoinedClubsNotifications(String name, int page)
	{
		ArrayList<ClubNotification> notifications = new ArrayList<ClubNotification>();
		User user = userRepository.findByUsername(name);
		for(Club club: user.getJoinedClubs())
			notifications.addAll(clubNotificationRepository.findAllByClubId(club.getId()));
		
		if(page * 5 >= notifications.size())
			return new ArrayList<>();
		
		ArrayList<ClubNotificationDto> notificationDtos = (ArrayList<ClubNotificationDto>) notifications
				.stream()
				.map(notification -> { return new ClubNotificationDto(notification);})
				.collect(Collectors.toList());
		
		Collections.sort(notificationDtos, Collections.reverseOrder());
		return notificationDtos.subList(page * 5, Math.min(page * 5 + 5, notificationDtos.size()));
	}
	
	public Boolean addClubNotification(String username, ClubNotificationDto notificationDto) {
		Club c = clubRepository.findByName(notificationDto.getClubName());
		if(c == null)
			return false;
		
		ClubNotification notification = new ClubNotification(notificationDto);
		notification.setSenderName(username);
		notification.setClub(c);
		notification.setTimestamp(LocalDateTime.now());
		clubNotificationRepository.save(notification);
		return true;
	}
	
	public boolean store(MultipartFile file, String club)
	{
		if (!file.isEmpty()) {
			try {	
				Club c = clubRepository.findByName(club);
				if(c == null)
					return false;
				
				//delete old logo
				Optional<ClubLogo> prevImage = clubLogoRepository.findByClubId(c.getId());
				if(prevImage.isPresent())
					clubLogoRepository.delete(prevImage.get());
				
				//save new logo
				ClubLogo image = new ClubLogo(file.getBytes(), c);
				c.setClubLogo(image);
				clubRepository.save(c);
	            return true;
			} catch (Exception e)
			{
				e.printStackTrace();
            	return false;
			}
		}
		return false;
	}
	
	public Resource loadAsResource(String clubName)
	{
		Club c = clubRepository.findByName(clubName);
		
		//if club doesn't exist simply return nothing
		if(c == null)
			return new InputStreamResource(new ByteArrayInputStream(new byte[] {}));
		
		Optional<ClubLogo> image = (clubLogoRepository.findByClubId(c.getId()));
		InputStreamResource resource = null;

		if(image.isPresent())
			resource = new InputStreamResource(new ByteArrayInputStream(image.get().getImage()));
		
		return resource;
	}
	
	public Boolean getIsMember(String username, String club) {
		User user = userRepository.findByUsername(username);
		if(user == null)
			return false;
		
		Club club_ = clubRepository.findByName(club);
		if(club_ == null)
			return false;
		
		return user.getJoinedClubs().contains(club_);
	}
}
