package edu.iastate.myclub.models.event;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import edu.iastate.myclub.models.club.Club;

/**
 * Data transfer object for Event
 * @author Graham Mobley
 *
 */
public class EventDto {
	
	private String clubName;

	private String title;
	
	private String date;
	
	private String time;
	
	private String description;
	
	public EventDto()
	{
		this.clubName = "";
		this.title = "";
		this.date = "";
		this.time = "";
		this.description = "";
	}
	public EventDto(Event event)
	{
		this.clubName = event.getClub().getName();
		this.title = event.getTitle();
		this.date = event.getDate();
		this.time = event.getTime();
		this.description = event.getDescription();
	}
	
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString()
	{
		return "{\"title\":\"" + title + "\",\"date\":\"" + date
				+ "\",\"time\":\"" + time + "\"}";
	}
	
	@Override
	public int hashCode()
	{
		return title.hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		
		if(!(o instanceof EventDto))
			return false;
		
		return ((EventDto)o).title.contentEquals(title);
	}
}
