package service

import model.Person
import repository.PersonRepository
import java.util.*

class PersonService(
    private val personRepository: PersonRepository
): EntityService<Person, String> {
    override fun getAllEntities(): List<Person> {
        return personRepository.findAll()
    }

    override fun deleteEntity(entity: Person) {
        personRepository.delete(entity)
    }

    override fun createEntity(entity: Person): Person {
        return personRepository.save(entity)
    }

    override fun getEntityById(id: String): Optional<Person> {
        return personRepository.findById(id)
    }
}