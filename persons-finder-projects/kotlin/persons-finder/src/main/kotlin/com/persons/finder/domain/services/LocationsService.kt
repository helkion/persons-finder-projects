package com.persons.finder.domain.services

import com.persons.finder.data.IndexField
import com.persons.finder.data.Location

interface LocationsService {
    fun addLocation(location: Location): Location
    fun removeLocation(locationReferenceId: Long)
    fun findAround(latitude: Double, longitude: Double, radiusInKm: Double) : List<IndexField>
    fun findAround(referenceId: Long, radiusInKm: Double): List<IndexField>


    /*
    * GET API to retrieve people around query location with a radius in KM,
    *   Use query param for radius.
    *   Extra challenge: Return list ordered by distance to each person.
    * GET API to retrieve a person or persons name using their ids
    * */
}