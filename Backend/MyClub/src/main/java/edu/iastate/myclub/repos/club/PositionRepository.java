package edu.iastate.myclub.repos.club;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.iastate.myclub.models.Position;

@Repository
public interface PositionRepository extends CrudRepository<Position, Integer> {

	Position findByName(String name);
}
