package edu.iastate.myclub.models.club;

import java.util.List;
import java.util.stream.Collectors;

import edu.iastate.myclub.models.ContactDetailsDto;

/**
 * Data transfer object for adding and updating clubs
 * @author Graham Mobley
 *
 */
public class ClubDto {

	private String name;

	private String description;
	
	private String meetingTimes;
	
	private String eventInformation;
	
	private String fees;
	
	private String membershipRestrictions;
	
	private List<String> officerPositions;
	
	private String electionInformation;
	
	private String constitution;
		
	private List<ContactDetailsDto> contacts;
	
	private int numMembers;
		
	public ClubDto() 
	{
		name = "";                     
		description = "";              
		meetingTimes = "";             
		eventInformation = "";         
		fees = "";                     
		membershipRestrictions = "";         
		electionInformation = "";   
		constitution = "";
		numMembers = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeetingTimes() {
		return meetingTimes;
	}

	public void setMeetingTimes(String meetingTimes) {
		this.meetingTimes = meetingTimes;
	}

	public String getEventInformation() {
		return eventInformation;
	}

	public void setEventInformation(String eventInformation) {
		this.eventInformation = eventInformation;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getMembershipRestrictions() {
		return membershipRestrictions;
	}

	public void setMembershipRestrictions(String membershipRestrictions) {
		this.membershipRestrictions = membershipRestrictions;
	}

	public List<String> getOfficerPositions() {
		return officerPositions;
	}

	public void setOfficerPositions(List<String> officerPositions) {
		this.officerPositions = officerPositions;
	}

	public String getElectionInformation() {
		return electionInformation;
	}

	public void setElectionInformation(String electionInformation) {
		this.electionInformation = electionInformation;
	}
	
	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public List<ContactDetailsDto> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactDetailsDto> contacts) {
		this.contacts = contacts;
	}
	
	public int getNumMembers() {
		return numMembers;
	}

	public void setNumMembers(int numMembers) {
		this.numMembers = numMembers;
	}

	public void copyFromClub(Club c)
	{
		this.name = c.getName();
		this.description = c.getDescription();
		this.meetingTimes = c.getMeetingTimes();
		this.eventInformation = c.getEventInformation();
		this.fees = c.getFees();
		this.membershipRestrictions = c.getMembershipRestrictions();
		this.constitution = c.getConstitution();
		this.officerPositions = c.getOfficerPositions()
				.stream()
				.map(position -> {return position.getName();})
				.collect(Collectors.toList());
		this.electionInformation = c.getElectionInformation();
		this.contacts = c.getContacts()
				.stream()
				.map(contactDetails -> {return new ContactDetailsDto(contactDetails);})
				.collect(Collectors.toList());
		this.numMembers = c.getNumMembers();
	}

	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		
		if(!(o instanceof ClubDto))
			return false;
		
		return ((ClubDto)o).name.contentEquals(this.name);
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}
