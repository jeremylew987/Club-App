package edu.iastate.myclub.models;

/**
 * Data transfer object for contact details
 * @author Graham Mobley
 *
 */
public class ContactDetailsDto {

	private String name;
	
	private String phoneNumber;
	
	private String email;
	
	public ContactDetailsDto()
	{
		this.name = "";
		this.phoneNumber = "";
		this.email = "";
	}
	public ContactDetailsDto(String name, String phoneNumber, String email)
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public ContactDetailsDto(ContactDetails cd)
	{
		this.name = cd.getName();
		this.email = cd.getEmail();
		this.phoneNumber = cd.getPhoneNumber();
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
}
