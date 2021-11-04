package edu.iastate.myclub.models;

/**
 * Data transfer object for a club type
 * TODO may need to change name of this since it only captures 
 * a few club details and dto description doesn't seem like
 * the best choice of describing this type
 * @author Graham Mobley
 *
 */
public class ClubDto {

	private String name;
	
	private String meetingTimes;
	
	private byte[] logo;
	
	public ClubDto(Club club)
	{
		this.setName(club.getName());
		this.setMeetingTimes(club.getMeetingTimes());
		this.setLogo(club.getLogo());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeetingTimes() {
		return meetingTimes;
	}

	public void setMeetingTimes(String meetingTimes) {
		this.meetingTimes = meetingTimes;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}