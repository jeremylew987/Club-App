package edu.iastate.myclub.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	@Column(name="id")
	private int id;
	
	@NotNull
	@ManyToOne
	private Club club;
	
	@NotNull
	@Column(name="sender_name")
	private String senderName;
	
	@NotNull
	@Column(name="message")
	private String message;
	
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
}