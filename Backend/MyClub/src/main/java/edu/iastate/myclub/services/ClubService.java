package edu.iastate.myclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iastate.myclub.models.Club;
import edu.iastate.myclub.repos.club.ClubRepository;

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
	
	public List<Club> getJoinedClubs(String name)
	{
		
		return null;
	}
	
	public List<Club> findClubs(String phrase)
	{
		
		return null;
	}
	
	public List<Club> getJoinedClubsNotifications(String name)
	{
		
		return null;
	}
}
