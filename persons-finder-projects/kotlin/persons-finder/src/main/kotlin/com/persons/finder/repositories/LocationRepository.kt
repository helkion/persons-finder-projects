package com.persons.finder.repositories

import com.persons.finder.data.Location
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface LocationRepository : CrudRepository<Location, Long> {
    fun findByReferenceId(id: Long): Optional<Location>
    fun findByLatitudeAndLongitude(latitude: Double, longitude: Double): Optional<Location>
}