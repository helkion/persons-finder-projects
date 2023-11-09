package com.persons.finder.domain.services

import com.persons.finder.data.Person

interface PersonsService {
    fun getById(id: Long): Person
    fun save(person: Person): Person
    fun findAllByIds(ids: List<Long>): MutableIterable<Person>?
}