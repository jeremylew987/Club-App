package edu.iastate.myclub.models.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import edu.iastate.myclub.models.club.Club;

@Entity
@Table(name="club_events")
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name="title")
	private String title;
	
	//Could use single date but for simplicity will separate date from time
	@NotNull
	@Column(name="date")
	private String date;
	
	@NotNull
	@Column(name="time")
	private String time;
	
	@NotNull
	@ManyToOne
	private Club club;
	
	public Event() {}
	public Event(EventDto eventDto)
	{
		this.title = eventDto.getTitle();
		this.date = eventDto.getDate();
		this.time = eventDto.getTime();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	
	public void copyFromEventDto(EventDto eventDto)
	{
		this.title = eventDto.getTitle();
		this.date = eventDto.getDate();
		this.time = eventDto.getTime();
	}
}
