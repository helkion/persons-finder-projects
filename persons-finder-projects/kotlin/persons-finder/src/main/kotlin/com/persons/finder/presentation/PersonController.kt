package com.persons.finder.presentation

import com.persons.finder.data.IndexField
import com.persons.finder.data.Person
import com.persons.finder.domain.services.PersonsService
import com.persons.finder.repositories.PersonRepository
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/persons")
class PersonController @Autowired constructor(val personService: PersonsService) {

    /*
        TODO PUT API to update/create someone's location using latitude and longitude
        (JSON) Body
     */
    //see LocationController

    /*
        TODO POST API to create a 'person'
        (JSON) Body and return the id of the created entity
    */
    @PostMapping("")
    fun save(@RequestBody person: Person): ResponseEntity<IndexField> {
        println("save")
        var status = HttpStatus.CREATED

        var index: IndexField? = null

        try {
            val savedPerson = personService.save(person)
            val id = savedPerson.id
            index = IndexField(id)
        } catch (e: ConstraintViolationException) {
            status = HttpStatus.BAD_REQUEST
        } catch (e: Exception) {
            status = HttpStatus.INTERNAL_SERVER_ERROR
            e.printStackTrace()
        }

        return ResponseEntity(index, status)
    }

    /*
        TODO GET API to retrieve people around query location with a radius in KM, Use query param for radius.
        TODO API just return a list of persons ids (JSON)
        // Example
        // John wants to know who is around his location within a radius of 10km
        // API would be called using John's id and a radius 10km
     */
    //see LocationController

    /*
        TODO GET API to retrieve a person or persons name using their ids
        // Example
        // John has the list of people around them, now they need to retrieve everybody's names to display in the app
        // API would be called using person or persons ids
     */

    @GetMapping("")
    fun getExample(): String {
        return "Hello Example"
    }

}