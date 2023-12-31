package com.persons.finder.domain.services

import com.persons.finder.data.Person
import com.persons.finder.repositories.PersonRepository
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonsServiceImpl : PersonsService {

    @Autowired
    private var personRepository: PersonRepository? = null

    constructor(personRepository: PersonRepository?){
        this.personRepository = personRepository;
    }

    override fun getById(id: Long): Person {
        TODO("Not yet implemented")
    }

    @Throws(ConstraintViolationException::class)
    override fun save(person: Person): Person {
        return personRepository?.save(person) ?:
            throw Exception("PersonRepository is not available")
    }

    override fun findAllByIds(ids: List<Long>): MutableIterable<Person>? {
        return personRepository?.findAllById(ids)
    }


}