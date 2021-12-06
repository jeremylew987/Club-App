package edu.iastate.myclub.repos.club;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.iastate.myclub.models.club.ClubNotification;

public interface ClubNotificationRepository extends PagingAndSortingRepository<ClubNotification, Integer> {

	//List<ClubNotification> findAllByClubIdAndOrderByTimestampAsc(int clubId);
	List<ClubNotification> findAllByClubId(int clubId);
}
