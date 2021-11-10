package edu.iastate.myclub.models.club;

import java.util.List;

import edu.iastate.myclub.models.ContactDetailsDto;
import edu.iastate.myclub.models.event.Event;
import edu.iastate.myclub.models.event.EventDto;

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
	
	private byte[] logo;
	
	private List<ContactDetailsDto> contacts;
	
	private List<EventDto> events;

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

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	
	public List<ContactDetailsDto> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactDetailsDto> contacts) {
		this.contacts = contacts;
	}

	public List<EventDto> getEvents() {
		return events;
	}

	public void setEvents(List<EventDto> events) {
		this.events = events;
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
