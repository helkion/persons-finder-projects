package com.persons.finder.data

import javax.persistence.Entity

@Entity
data class Location(
    // Tip: Person's id can be used for this field
    var referenceId: Long,
    var latitude: Double,
    var longitude: Double

) : IndexField() {
    constructor() : this(0L, 0.0, 0.0) {
    }
}
