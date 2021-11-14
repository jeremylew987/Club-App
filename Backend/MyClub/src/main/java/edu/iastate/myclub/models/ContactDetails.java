package edu.iastate.myclub.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import edu.iastate.myclub.models.club.Club;

/**
 * Model defining contact details for a person
 * 
 * @author Graham Mobley
 *
 */
@Entity
@Table(name="contact_details")
public class ContactDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="phone_number")
	private String phoneNumber;
	
	@NotNull
	@Column(name="email")
	private String email;
	
	@NotNull
	@ManyToOne
	private Club club;
	
	public ContactDetails() {}
	public ContactDetails(String name, String phoneNumber, String email)
	{
		this.setName(name);
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
}
