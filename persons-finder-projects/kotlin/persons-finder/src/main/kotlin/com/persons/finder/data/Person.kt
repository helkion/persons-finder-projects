package com.persons.finder.data

import javax.persistence.Entity

@Entity
data class Person (
    var name: String = ""
) : IndexField()


