package edu.iastate.myclub.models;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import edu.iastate.myclub.models.club.Club;

/**
 * Data transfer object for contact details
 * @author Graham Mobley
 *
 */
public class ContactDetailsDto {

	private String name;
	
	private String phoneNumber;
	
	private String email;
	
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
}
