package edu.iastate.myclub.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import edu.iastate.myclub.models.club.Club;

@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "uid")
	int Uid;
	
	@NotNull
	@Column(name="firstName")
	String firstName;
	
	
	@NotNull
	@Column(name="lastName")
	String lastName;
	
	@NotNull
	@Column(name="passphrase")
	String passphrase; 
	
	@NotNull
	@Column(name="username")
	String username;
	
	@ManyToMany
	@JoinTable(name="club_membership",
			joinColumns=@JoinColumn(name="uid", referencedColumnName="uid"),
			inverseJoinColumns=@JoinColumn(name="club_id", referencedColumnName="id"))
	private Set<Club> joinedClubs;
	
	public User() {
		
	}

	public User(@NotNull String firstName, @NotNull String lastName, @NotNull String passphrase,
			@NotNull String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.passphrase = passphrase;
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Club> getJoinedClubs() {
		return joinedClubs;
	}

	public void setJoinedClubs(Set<Club> joinedClubs) {
		this.joinedClubs = joinedClubs;
	}
}
