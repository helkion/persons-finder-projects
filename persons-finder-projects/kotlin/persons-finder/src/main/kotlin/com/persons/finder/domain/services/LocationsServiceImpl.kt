package com.persons.finder.domain.services

import com.persons.finder.data.IndexField
import com.persons.finder.data.Location
import com.persons.finder.data.Person
import com.persons.finder.repositories.LocationRepository
import com.persons.finder.repositories.PersonRepository
import com.persons.finder.utils.HaversineUtils
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationsServiceImpl : LocationsService {

    @Autowired
    private val locationRepository: LocationRepository? = null

    @Throws(ConstraintViolationException::class)
    override fun addLocation(location: Location): Location {
        return locationRepository?.save(location) ?:
        throw Exception("LocationRepository is not available")
    }

    override fun removeLocation(locationReferenceId: Long) {
        TODO("Not yet implemented")
    }

    override fun findAround(latitude: Double, longitude: Double, radiusInKm: Double): List<IndexField> {

        val list = locationRepository?.findAll()

        val withinRadius = { l: Location ->
            HaversineUtils.calculateDistance(
                latitude, longitude, l.latitude, l.longitude
            ) < radiusInKm
        }

        val locations = list
            ?.filter(withinRadius)
            ?.map { l ->
                IndexField(l.id)
            }
        return locations!!
    }

    override fun findAround(referenceId: Long, radiusInKm: Double): List<IndexField> {
        val opt = locationRepository?.findByReferenceId(referenceId)
        val location = opt?.orElseThrow()

        var latitude = location?.latitude
        var longitude = location?.longitude

        val locations = findAround(latitude!!, longitude!!, radiusInKm)

        return locations
    }


}