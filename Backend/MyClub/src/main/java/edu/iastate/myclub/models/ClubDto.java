package edu.iastate.myclub.models;

/**
 * Data transfer object for a club type
 * 
 * @author Graham Mobley
 *
 */
public class ClubDto {

	private String name;
	
	private String description;
	
	private byte[] logo;
	
	public ClubDto(Club club)
	{
		this.setName(club.getName());
		this.setDescription(club.getDescription());
		this.setLogo(club.getLogo());
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

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
}
