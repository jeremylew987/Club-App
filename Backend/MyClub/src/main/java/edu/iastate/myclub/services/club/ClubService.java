package edu.iastate.myclub.services.club;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.iastate.myclub.models.club.Club;
import edu.iastate.myclub.models.club.ClubBasicDto;
import edu.iastate.myclub.models.club.ClubDto;
import edu.iastate.myclub.models.club.ClubLogo;
import edu.iastate.myclub.models.club.ClubNotification;
import edu.iastate.myclub.repos.club.ClubLogoRepository;
import edu.iastate.myclub.repos.club.ClubRepository;
import edu.iastate.myclub.repos.club.ContactDetailsRepository;
import edu.iastate.myclub.repos.club.PositionRepository;

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
	
	public ClubService() {}
	public ClubService(ClubRepository clubRepository, ContactDetailsRepository contactDetailsRepository,
			PositionRepository positionRepository, ClubLogoRepository clubLogoRepository)
	{
		this.clubRepository = clubRepository;
		this.contactDetailsRepository = contactDetailsRepository;
		this.positionRepository = positionRepository;
		this.clubLogoRepository = clubLogoRepository;
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
		
		clubRepository.save(c.copyFromClubDto(club, positionRepository, contactDetailsRepository, true));
		return true;
	}
	
	public List<ClubBasicDto> getJoinedClubs(String name)
	{
		ArrayList<Club> clubs = new ArrayList<Club>();
		//User user = userRepository.findByName(name);
		//for(Club club: user.getClubs())
		//	clubs.add(new ClubDto(club));
		//return clubs;
		return null;
	}
	
	public List<ClubBasicDto> findClubs(String phrase, int page, int size)
	{
		Pageable pageAndSortByName = PageRequest.of(page, size);
		List<Club> clubs = clubRepository.findAllByNameContainingOrderByNameAsc(phrase, pageAndSortByName);
		return (List<ClubBasicDto>) clubs.stream().map(club -> new ClubBasicDto(club)).collect(Collectors.toList());
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
	
	public List<ClubNotification> getJoinedClubsNotifications(String name)
	{
		//User user = userRepository.findByName(name);
		//for(Club club: user.getClubs())
		//{
		//	add club notifications to priority queue, sorted by timestamp
		//}
		return null;
	}
	
	public boolean store(MultipartFile file, String club)
	{
		if (!file.isEmpty()) {
			try {	
				Club c = clubRepository.findByName(club);
				if(c == null)
					return false;
				
				//delete old logo
				Optional<ClubLogo> prevImage = (Optional<ClubLogo>)clubLogoRepository.findByClubId(c.getId());
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
		
		Optional<ClubLogo> image = ((Optional<ClubLogo>)clubLogoRepository.findByClubId(c.getId()));
		InputStreamResource resource = null;

		if(image.isPresent())
			resource = new InputStreamResource(new ByteArrayInputStream(image.get().getImage()));
		
		return resource;
	}
}
