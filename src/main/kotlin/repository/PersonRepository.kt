package repository

import jakarta.persistence.EntityManager
import model.Person
import repository.base.EntityRepositoryImpl

class PersonRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<Person, String>(entityManager) {
    override fun getEntityClass(): Class<Person> {
        return Person::class.java
    }
}