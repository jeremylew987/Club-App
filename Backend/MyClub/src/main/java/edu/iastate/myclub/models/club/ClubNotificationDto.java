package edu.iastate.myclub.models.club;

import java.time.LocalDateTime;

/**
 * 
 * Data transfer object for club notifications
 * 
 * @author Graham Mobley
 *
 */
public class ClubNotificationDto implements Comparable<ClubNotificationDto> {

	private String senderName;
	
	private String message;
	
	private LocalDateTime timestamp;
	
	private String clubName;
	
	public ClubNotificationDto() {}
	public ClubNotificationDto(ClubNotification notification)
	{
		this.setSenderName(notification.getSenderName());
		this.setMessage(notification.getMessage());
		this.setTimestamp(notification.getTimestamp());
		this.setClubName(notification.getClub().getName());
	}
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	
	@Override
	public int compareTo(ClubNotificationDto o) {
		return this.timestamp.compareTo(o.timestamp);
	}
}
