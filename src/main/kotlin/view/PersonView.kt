package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jakarta.persistence.EntityManager
import model.Person
import repository.PersonRepository
import service.PersonService
import java.time.Instant

class PersonView {
    @Composable
    @Preview
    fun PersonRequests(
        viewRequests: Map<String, Boolean>,
        setViewRequests: (Map<String, Boolean>) -> Unit,
        entityManager: EntityManager
    ) {
        val personService = PersonService(PersonRepository(entityManager))
        var personDetails by remember { mutableStateOf(mapOf(
            "id" to "",
            "first_name" to "",
            "last_name" to "",
            "email" to "",
            "phone" to "",
            "address" to ""
        )) }

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // CREATE BUTTON
                Button(
                    onClick = {
                        println(
                            personService.createEntity(
                                Person(
                                    firstName = personDetails["first_name"]!!,
                                    lastName = personDetails["last_name"]!!,
                                    email = personDetails["email"]!!,
                                    phone = personDetails["phone"]!!,
                                    address = personDetails["address"]!!,
                                    createdAt = Instant.now(),
                                    updatedAt = Instant.now()
                                )
                            )
                        )
                    }
                ) {
                    Text("Create Person")
                }

                Button(
                    onClick = {
                        personService.deleteEntityById(personDetails["id"]!!)
                    }
                ) {
                    Text("Delete Person by ID")
                }

                Button(
                    onClick = {
                        if (personDetails["id"]!! != "") {
                            println(personService.getEntityById(personDetails["id"]!!))
                        }
                    }
                ) {
                    Text("Get Person by ID")
                }

                Button(
                    onClick = {
                        println(personService.getAllEntities())
                    }
                ) {
                    Text("Get Persons")
                }

                Button(
                    onClick = {
                        setViewRequests(viewRequests.plus("person" to false))
                    }
                ) {
                    Text("Back")
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = personDetails["id"]!!,
                    onValueChange = { personDetails = personDetails.plus("id" to it) },
                    label = { Text("ID") }
                )

                TextField(
                    value = personDetails["first_name"]!!,
                    onValueChange = { personDetails = personDetails.plus("first_name" to it) },
                    label = { Text("First Name") }
                )

                TextField(
                    value = personDetails["last_name"]!!,
                    onValueChange = { personDetails = personDetails.plus("last_name" to it) },
                    label = { Text("Last Name") }
                )

                TextField(
                    value = personDetails["email"]!!,
                    onValueChange = { personDetails = personDetails.plus("email" to it) },
                    label = { Text("Email") }
                )

                TextField(
                    value = personDetails["phone"]!!,
                    onValueChange = { personDetails = personDetails.plus("phone" to it) },
                    label = { Text("Phone") }
                )

                TextField(
                    value = personDetails["address"]!!,
                    onValueChange = { personDetails = personDetails.plus("address" to it) },
                    label = { Text("Address") }
                )
            }
        }
    }
}