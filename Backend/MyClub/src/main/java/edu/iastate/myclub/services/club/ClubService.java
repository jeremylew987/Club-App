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
	
	public boolean createClub(ClubDto club)
	{
		//TODO decide whether to do additional validation here and return false in case 
		//new club is bad
		clubRepository.save(new Club().copyFromClubDto(club, positionRepository, contactDetailsRepository));
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
		
		clubRepository.save(c.copyFromClubDto(club, positionRepository, contactDetailsRepository));
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
	
	public List<ClubBasicDto> findClubs(String phrase, String page, int size)
	{
		List<ClubBasicDto> clubs = new ArrayList<ClubBasicDto>() {
			{
				add(new ClubBasicDto());
			}
		};
		return clubs;
//		Pageable pageAndSortByName = PageRequest.of(page, size);
//		List<Club> clubs = clubRepository.findAllByNameOrderByNameAsc(phrase, pageAndSortByName);
//		return (List<ClubBasicDto>) clubs.stream().map(club -> new ClubBasicDto(club)).collect(Collectors.toList());
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
				
				ClubLogo image = clubLogoRepository.save(new ClubLogo(club, file.getBytes(), c));
	            return true;
			} catch (Exception e)
			{
            	return false;
			}
		}
		return false;
	}
	
	public Resource loadAsResource(String clubName)
	{
		Optional<ClubLogo> image = ((Optional<ClubLogo>)clubLogoRepository.findByClubName(clubName));
		InputStreamResource resource = null;

		if(image.isPresent())
			resource = new InputStreamResource(new ByteArrayInputStream(image.get().getImage()));
		
		return resource;
	}
}
