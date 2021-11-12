package edu.iastate.myclub.models.club;

/**
 * Data transfer object for a club type
 * TODO may need to change name of this since it only captures 
 * a few club details and dto description doesn't seem like
 * the best choice of describing this type
 * @author Graham Mobley
 *
 */
public class ClubBasicDto {

	private String name;
	
	private String meetingTimes;
		
	public ClubBasicDto() {this.name = "testing"; this.meetingTimes = "test";}
	public ClubBasicDto(Club club)
	{
		this.setName(club.getName());
		this.setMeetingTimes(club.getMeetingTimes());
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
	
	@Override
	public String toString()
	{
		//consider using library for json serialization
		return "{\"name\":\"" + name + "\",\"meetingTimes\":\"" + meetingTimes + "\"}";
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
		
		if(!(o instanceof ClubBasicDto))
			return false;
		
		return ((ClubBasicDto)o).name.contentEquals(name);
	}
}