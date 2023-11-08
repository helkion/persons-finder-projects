/**
 * 
 */
package com.persons.finder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persons.finder.data.Person;

/**
 * 
 */
public interface PersonRepository 
	extends JpaRepository<Person, Long> {
	
	//GET API to retrieve a person or persons name using their ids
	
//	List<Person> findAllByIdIn(List<IndexField> ids);
	
}
