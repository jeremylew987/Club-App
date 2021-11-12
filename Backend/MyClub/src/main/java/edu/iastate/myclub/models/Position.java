package edu.iastate.myclub.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import edu.iastate.myclub.models.club.Club;

/**
 * Model defining a club position
 * 
 * @author Graham Mobley
 *
 */
@Entity
@Table(name="positions")
public class Position {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@ManyToMany(mappedBy="officerPositions")
	private List<Club> clubs;
	
	public Position() {}
	public Position(String name)
	{
		this.name = name;
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
}
