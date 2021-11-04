package edu.iastate.myclub.models;

public class ClubDto {

	private String name;
	
	private String description;
	
	private byte[] logo;
	
	public ClubDto(Club club)
	{
		this.name = club.getName();
		this.description = club.getDescription();
		this.logo = club.getLogo();
	}
}
