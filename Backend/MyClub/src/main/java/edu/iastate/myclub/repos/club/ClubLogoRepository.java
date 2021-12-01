package edu.iastate.myclub.repos.club;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.iastate.myclub.models.club.ClubLogo;

@Repository
public interface ClubLogoRepository extends CrudRepository<ClubLogo, Integer> {

	Optional<ClubLogo> findByClubId(int clubId);
}
