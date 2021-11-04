package edu.iastate.myclub.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	private int id;
	
	@NotNull
	@Column(name="club_name")
	private String clubName;
	
	@NotNull
	@Column(name="sender_name")
	private String senderName;
	
	@NotNull
	@Column(name="message")
	private String message;
	
	@NotNull
	@Column(name="logo")
	private byte[] logo;
	
	public ClubNotification() {}
	public ClubNotification(Club club)
	{
		this.setClubName(club.getName());
		this.setLogo(club.getLogo());
		this.setSenderName("");
		this.setMessage("");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
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
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
}