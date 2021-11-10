package edu.iastate.myclub.services.club;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.iastate.myclub.models.club.Club;
import edu.iastate.myclub.models.club.ClubBasicDto;
import edu.iastate.myclub.models.club.ClubNotification;
import edu.iastate.myclub.repos.club.ClubRepository;

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
	
	public boolean createClub(Club club)
	{
		//TODO decide whether to do additional validation here and return false in case 
		//new club is bad
		clubRepository.save(club);
		return true;
	}
	
	//TODO decide how to identify specific club entry in database to modify (possibly by name)
	public boolean modifyClub(Club club)
	{
		//TODO decide whether to do additional validation here and return false in case 
		//new club is bad
		clubRepository.save(club);
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
		List<Club> clubs = clubRepository.findAllByNameOrderByNameAsc(phrase, pageAndSortByName);
		return (List<ClubBasicDto>) clubs.stream().map(club -> new ClubBasicDto(club)).collect(Collectors.toList());
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
}
