package com.persons.finder.presentation

import com.persons.finder.data.IndexField
import com.persons.finder.data.Location
import com.persons.finder.data.Person
import com.persons.finder.domain.services.LocationsService
import com.persons.finder.domain.services.PersonsService
import com.persons.finder.repositories.PersonRepository
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/locations")
class LocationController @Autowired constructor(val locationService: LocationsService) {

    /*
        TODO PUT API to update/create someone's location using latitude and longitude
        (JSON) Body
     */
    @PostMapping("/setlocation")
    fun setLocation(@RequestBody location: Location): ResponseEntity<Location> {
        println("setlocation")
        var status = HttpStatus.CREATED

        var savedLocation: Location? = null
        try {
            savedLocation = locationService.addLocation(location)
        } catch (e: ConstraintViolationException) {
            status = HttpStatus.BAD_REQUEST
        } catch (e: Exception) {
            status = HttpStatus.INTERNAL_SERVER_ERROR
            e.printStackTrace()
        }

        return ResponseEntity(savedLocation, status)
    }

    /*
        TODO GET API to retrieve people around query location with a radius in KM, Use query param for radius.
        TODO API just return a list of persons ids (JSON)
        // Example
        // John wants to know who is around his location within a radius of 10km
        // API would be called using John's id and a radius 10km
     */
    @GetMapping("/findaround/{referenceId}")
    fun findAround(
        @PathVariable(name = "referenceId") referenceId: Long,
        @RequestParam("radiusInKm") radiusInKm: Double
    ): List<IndexField> {
        println("findAround")
        var lista: List<IndexField>? = null

        try {
            lista = locationService.findAround(referenceId, radiusInKm)
        } catch (e: Exception) {
            if (e is NoSuchElementException) {
                println("Register not found!")
                throw Exception("Register not found!")
            }

            e.printStackTrace()
            throw e
        }

        return lista
    }

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