package edu.iastate.myclub.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	private int id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	@NotNull
	@Column(name="meetingTimes")
	private String meetingTimes;
	
	@NotNull
	@Column(name="eventInformation")
	private String eventInformation;
	
	@NotNull
	@Column(name="fees")
	private String fees;
	
	@NotNull
	@Column(name="membershipRestrictions")
	private String membershipRestrictions;
	
	@NotNull
	@Column(name="numStudents")
	private int numStudents;
	
	@NotNull
	@Column(name="numISUMembers")
	private int numISUMembers;
	
	@NotNull
	@Column(name="numNonISUMembers")
	private int numNonISUMembers;
	
	@ManyToMany
	@JoinTable(name="club_positions",
				joinColumns = @JoinColumn(name="club_id", referencedColumnName="id"),
				inverseJoinColumns = @JoinColumn(name="position_id", referencedColumnName="id"))
	private Set<Position> officerPositions;
	
	private String elections;

	
	//@OneToMany
	//private List<User> members;
	
	@Column(name="logo")
	private byte[] logo;

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

	public int getNumStudents() {
		return numStudents;
	}

	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}

	public int getNumISUMembers() {
		return numISUMembers;
	}

	public void setNumISUMembers(int numISUMembers) {
		this.numISUMembers = numISUMembers;
	}

	public int getNumNonISUMembers() {
		return numNonISUMembers;
	}

	public void setNumNonISUMembers(int numNonISUMembers) {
		this.numNonISUMembers = numNonISUMembers;
	}

	public Set<Position> getOfficerPositions() {
		return officerPositions;
	}

	public void setOfficerPositions(Set<Position> officerPositions) {
		this.officerPositions = officerPositions;
	}

	public String getElections() {
		return elections;
	}

	public void setElections(String elections) {
		this.elections = elections;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
}
