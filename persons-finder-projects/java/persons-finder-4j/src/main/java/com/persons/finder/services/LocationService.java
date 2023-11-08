/**
 * 
 */
package com.persons.finder.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persons.finder.data.IndexField;
import com.persons.finder.data.Location;
import com.persons.finder.repositories.LocationRepository;
import com.persons.finder.utils.HaversineUtils;


/**
 * 
 */
@Service
public class LocationService implements Serializable{

	private static final long serialVersionUID = 4489416661212522099L;
	
	@Autowired
    private LocationRepository locationRepository;

    public Location save(Location location) 
    		throws ConstraintViolationException{
    	location = locationRepository.save(location);
        return location;
    }
    
    public List<Location> getByReferenceId(Long id) 
    		throws Exception {
    	List<Location> list = id != null ?
    			Stream.of(
    					locationRepository.findByReferenceId(id)
    						.orElseThrow(Exception::new))
    							.toList() 
    			: locationRepository.findAll();
    	return list;
    }
    
    
    // TODO GET API to retrieve people around query location with a radius in KM, 
    // Use query param for radius. 
    // Extra challenge: Return list ordered by distance to each person.
    public List<IndexField> findAround(
    		long referenceId,
    		double radiusInKm) 
    				throws Exception{
    	
    	
    	Optional<Location> opt = 
    			locationRepository.findByReferenceId(referenceId);
    	
    	Location location = opt.orElseThrow();
    	
    	double latitude = location.getLatitude();
		double longitude = location.getLongitude();
    	
    	List<Location> list = locationRepository.findAll();
    	
    	Predicate<Location> withinRadius = 
    			l -> HaversineUtils.calculateDistance(
    					latitude, 
    					longitude, 
		    			l.getLatitude(), 
		    			l.getLongitude()) < radiusInKm;
    	
    	List<IndexField> locations = list
    			.stream()
    			.filter(withinRadius)
    			.map(l -> 
    				new IndexField(l.getId())
    			)
    			.toList();
    	
    	return locations;
    }

 
}
