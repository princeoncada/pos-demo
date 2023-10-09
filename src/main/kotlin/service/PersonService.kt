package service

import model.Person
import repository.PersonRepository
import service.base.EntityServiceImpl

class PersonService(
    personRepository: PersonRepository
): EntityServiceImpl<Person, String>(personRepository) {}