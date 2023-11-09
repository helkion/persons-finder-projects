/**
 * 
 */
package com.persons.finder.controllers;

import java.io.Serializable;
import java.util.List;

import javax.validation.ConstraintViolationException;

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
import com.persons.finder.data.Person;
import com.persons.finder.services.PersonService;

/**
 * 
 */
@RestController
@RequestMapping("api/v1/persons")
public class PersonController implements Serializable{

	private static final long serialVersionUID = -4154770591389875295L;
	
	@Autowired
    private PersonService personService;
	
	@GetMapping("")
	public String index() {
		
		return "Hello Persons";
	}

	/*
	 * TODO POST API to create a 'person'
        (JSON) Body and return the id of the created entity
	 * */
    @PostMapping("")
    public ResponseEntity<IndexField> save(
    		@RequestBody Person person) {
    	System.out.println("save");
    	HttpStatus status = HttpStatus.CREATED;
    	
    	IndexField index = null;
    	
    	try {
    		person = personService.save(person);
    		Long id = person.getId();
    		index = new IndexField();
    		index.setId(id);
    	} catch (ConstraintViolationException e) {
    		status = HttpStatus.BAD_REQUEST;
    		person = null;
    	} catch (Exception e) {
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    		person = null;
    		e.printStackTrace();
    	}
    	
        return new ResponseEntity<>(index, status);
    }
    
    @GetMapping(value = {"/{id}"})
    public List<Person> getById(
    		@PathVariable(name = "id") Long id) 
    				throws Exception {
    	System.out.println("getById");
    	List<Person> lista = null;
		try {
			lista = personService.getById(id);
		} catch (Exception e) {
//			if (!(e instanceof NotFoundException)) {
//				e.printStackTrace();
//			} else {
				System.err.println("Register not found!");
				e.printStackTrace();
//			}
			
			throw e;
		}
    	
    	return lista;
    }    
    
    /*
     * DO GET API to retrieve a person or persons name using their ids
        // Example
        // John has the list of people around them, now they need to retrieve everybody's names to display in the app
        // API would be called using person or persons ids
     * */
    @PostMapping(value = {"/names"})
    public List<Person> getNamesByIds(
    		@RequestBody() List<IndexField> indices) 
    				throws Exception {
    	System.out.println("getNamesByIds");
    	List<Person> lista = null;
		try {
			List<Long> ids = indices
					.stream()
					.map(i -> i.getId())
					.toList();
			lista = personService.findAllByIds(ids);
		} catch (Exception e) {
//			if (!(e instanceof NotFoundException)) {
//				e.printStackTrace();
//			} else {
				System.err.println("Register not found!");
				e.printStackTrace();
//			}
			
			throw e;
		}
    	
    	return lista;
    }   
    		
}
