/**
 * 
 */
package com.persons.finder.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persons.finder.data.Location;

/**
 * 
 */
public interface LocationRepository 
	extends JpaRepository<Location, Long> {
	
	Optional<Location> findByReferenceId(Long id);
	Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);
}
