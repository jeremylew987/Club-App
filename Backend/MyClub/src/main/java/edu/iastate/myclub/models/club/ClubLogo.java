package edu.iastate.myclub.models.club;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="club_logo")
public class ClubLogo {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Lob
	@NotNull
	@Column(name="image")
	private byte[] image;
	
	@NotNull
	@Column(name="club_name")
	private String clubName;
	
	@NotNull
	@OneToOne(mappedBy="clubLogo")
	private Club club;
	
	public ClubLogo() {}
	public ClubLogo(String clubName, byte[] logo, Club club)
	{
		this.clubName = clubName;
		this.image = logo;
		this.club = club;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
}
