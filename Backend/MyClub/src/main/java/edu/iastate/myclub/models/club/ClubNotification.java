package edu.iastate.myclub.models.club;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Model defining a club notification
 * 
 * @author Graham Mobley
 *
 */
@Entity
@Table(name="club_notification")
public class ClubNotification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@JsonIgnore
	@ManyToOne
	private Club club;
	
	@NotNull
	@Column(name="sender_name")
	private String senderName;
	
	@NotNull
	@Column(name="message")
	private String message;
	
	@NotNull
	@Column(name="timestamp")
	private String timestamp;
	
	@Transient
	private String clubName;
	
	public ClubNotification() {}
	public ClubNotification(Club club)
	{
		this.setSenderName("");
		this.setMessage("");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
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
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
}