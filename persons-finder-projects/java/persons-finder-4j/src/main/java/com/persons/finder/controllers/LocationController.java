/**
 * 
 */
package com.persons.finder.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persons.finder.data.IndexField;
import com.persons.finder.data.Location;
import com.persons.finder.services.LocationService;

/**
 * 
 */
@RestController
@RequestMapping("api/v1/locations")
public class LocationController implements Serializable{

	private static final long serialVersionUID = -4154770591389875295L;
	
	@Autowired
    private LocationService locationService;
	
	@GetMapping("")
	public String index() {
		
		return "Hello Locations";
	}

	/*
	 * TODO PUT API to update/create someone's location using latitude and longitude
        (JSON) Body
	 * */
    @PostMapping("/setlocation")
    public ResponseEntity<Location> setlocation(
    		@RequestBody Location location) {
    	System.out.println("setlocation");
    	HttpStatus status = HttpStatus.CREATED;
    	
    	try {
	    	location = locationService.save(location);
    	} catch (ConstraintViolationException e) {
    		status = HttpStatus.BAD_REQUEST;
    		location = null;
    	} catch (Exception e) {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    		location = null;
    		e.printStackTrace();
    	}
    	
        return new ResponseEntity<>(location, status);
    }
    
    /*
     * TODO GET API to retrieve people around query location with a radius in KM, Use query param for radius.
        TODO API just return a list of persons ids (JSON)
        // Example
        // John wants to know who is around his location within a radius of 10km
        // API would be called using John's id and a radius 10km
     * 
     * */
    @GetMapping(value = {"/findaround/{referenceId}"})
    public List<IndexField> findAround(
    		@PathVariable(name = "referenceId") Long referenceId,
    		@QueryParam("radiusInKm") double radiusInKm) 
    				throws Exception {
    	System.out.println("findAround");
    	List<IndexField> lista = null;
    	
		try {
			lista = locationService.findAround(
					referenceId, 
					radiusInKm);
		} catch (Exception e) {
			if (e instanceof NoSuchElementException) {
				System.err.println("Register not found!");
				e = new Exception("Register not found!");
			}
			
			e.printStackTrace();
			
			throw e;
		}
    	
    	return lista;
    }    
}
