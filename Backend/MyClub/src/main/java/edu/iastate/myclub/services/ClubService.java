package edu.iastate.myclub.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.iastate.myclub.models.Club;
import edu.iastate.myclub.models.ClubDto;
import edu.iastate.myclub.models.ClubNotification;
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
	
	public List<ClubDto> getJoinedClubs(String name)
	{
		ArrayList<Club> clubs = new ArrayList<Club>();
		//User user = userRepository.findByName(name);
		//for(Club club: user.getClubs())
		//	clubs.add(new ClubDto(club));
		//return clubs;
		return null;
	}
	
	public List<ClubDto> findClubs(String phrase, int page, int size)
	{
		//Page<Club> clubs = clubRepository.findAll(PageRequest.of(page, size), Sort.by(Sort.Direction.DESC, "name"));
		//return (List<ClubDto>) clubs.getContent().stream().map(club -> new ClubDto(club)).collect(Collectors.toList());
		return null;
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
