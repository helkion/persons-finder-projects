/**
 * 
 */
package com.persons.finder.services;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persons.finder.data.Person;
import com.persons.finder.repositories.PersonRepository;


/**
 * 
 */
@Service
public class PersonService implements Serializable{

	private static final long serialVersionUID = 4489416661212522099L;
	
	@Autowired
    private PersonRepository personRepository;

    public Person save(Person person) 
    		throws ConstraintViolationException{
    	person = personRepository.save(person);
        return person;
    }
    
    public List<Person> getById(Long id) 
    		throws Exception {
    	List<Person> list = id != null ?
    			Stream.of(
    					personRepository.findById(id)
    						.orElseThrow(Exception::new))
    							.toList() 
    			: personRepository.findAll();
    	return list;
    }
    
    public List<Person> findAllByIds(List<Long> ids) {
    	return personRepository.findAllById(ids);//personRepository.findByIdIn(ids);
    }

 
}
