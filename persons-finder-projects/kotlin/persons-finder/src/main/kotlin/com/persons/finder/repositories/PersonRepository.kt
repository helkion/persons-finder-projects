package com.persons.finder.repositories

import com.persons.finder.data.Person
import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, Long> {

}