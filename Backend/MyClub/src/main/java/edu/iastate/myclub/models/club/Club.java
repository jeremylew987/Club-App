package edu.iastate.myclub.models.club;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.iastate.myclub.models.ContactDetails;
import edu.iastate.myclub.models.Position;
import edu.iastate.myclub.models.event.Event;
import edu.iastate.myclub.models.user.User;
import edu.iastate.myclub.repos.club.ContactDetailsRepository;
import edu.iastate.myclub.repos.club.PositionRepository;

/**
 * Model defining a club
 * 
 * @author Graham Mobley
 *
 */
@Entity
@Table(name="club")
public class Club {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	@NotNull
	@Column(name="meeting_times")
	private String meetingTimes;
	
	@NotNull
	@Column(name="event_information")
	private String eventInformation;
	
	@NotNull
	@Column(name="fees")
	private String fees;
	
	@NotNull
	@Column(name="membership_restrictions")
	private String membershipRestrictions;
	
	@NotNull
	@JsonIgnore
	@Column(name="num_members")
	private int numMembers;
	
	@ManyToMany
	@JoinTable(name="club_positions",
				joinColumns = @JoinColumn(name="club_id", referencedColumnName="id"),
				inverseJoinColumns = @JoinColumn(name="position_id", referencedColumnName="id"))
	private Set<Position> officerPositions;
	
	@NotNull
	@Column(name="election_information")
	private String electionInformation;
	
	@NotNull
	@Column(name="constitution")
	private String constitution;

	@OneToMany
	@JoinColumn(name="club_id")
	private List<ClubNotification> notifications;
	
	@ManyToMany(mappedBy="joinedClubs")
	private List<User> members;
	
	@OneToMany
	@JoinColumn(name="club_id")
	private List<ContactDetails> contacts;
	
	@OneToMany
	@JsonIgnore
	@JoinColumn(name="club_id")
	private List<Event> events;
	
	@OneToOne(mappedBy="club", cascade=CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private ClubLogo clubLogo;
	
	public Club()
	{
		this.name = "";
		this.description ="";
		this.meetingTimes = "";
		this.eventInformation = "";
		this.fees = "";
		this.membershipRestrictions = "";
		this.numMembers = 0;
		this.electionInformation = "";
		this.constitution = "";
	}
	
	public Club(String name)
	{
		this.name = name;
		this.description ="";
		this.meetingTimes = "";
		this.eventInformation = "";
		this.fees = "";
		this.membershipRestrictions = "";
		this.numMembers = 0;
		this.electionInformation = "";
		this.constitution = "";
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

	public int getNumMembers() {
		return numMembers;
	}

	public void setNumMembers(int numMembers) {
		this.numMembers = numMembers;
	}

	public Set<Position> getOfficerPositions() {
		return officerPositions;
	}

	public void setOfficerPositions(Set<Position> officerPositions) {
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

	public List<ClubNotification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<ClubNotification> notifications) {
		this.notifications = notifications;
	}
	
	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<ContactDetails> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactDetails> contacts) {
		this.contacts = contacts;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public ClubLogo getClubLogo() {
		return clubLogo;
	}

	public void setClubLogo(ClubLogo clubLogo) {
		this.clubLogo = clubLogo;
	}

	public Club copyFromClubDto(ClubDto clubDto, PositionRepository repository, 
			ContactDetailsRepository contactDetailsRepository, boolean reuseContactDetails)
	{
		name = clubDto.getName();
		description = clubDto.getDescription();
		meetingTimes = clubDto.getMeetingTimes();
		eventInformation = clubDto.getEventInformation();
		fees = clubDto.getFees();
		membershipRestrictions = clubDto.getMembershipRestrictions();
		constitution = clubDto.getConstitution();
		officerPositions = clubDto.getOfficerPositions().stream().map(position -> 
		{
		Position p = repository.findByName(position);
		if(p != null)
			return p;
		else
			return repository.save(new Position(position));
		}).collect(Collectors.toSet());
		
		electionInformation = clubDto.getElectionInformation();
		contacts = clubDto.getContacts().stream().map(details -> 
		{
			if(reuseContactDetails)
			{
				ContactDetails c = contactDetailsRepository.findByNameAndClubId(details.getName(), id); //TODO change to query by unique field
				if(c != null)
					return c;
				else
					return contactDetailsRepository.save(new ContactDetails(details, this));
			}
			else
				return contactDetailsRepository.save(new ContactDetails(details, this));
		}).collect(Collectors.toList());
		
		System.out.println(constitution);
		return this;
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
		
		if(!(o instanceof Club))
			return false;
		
		return ((Club)o).name.contentEquals(this.name);
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}