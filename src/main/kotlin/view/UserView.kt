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
import model.User
import repository.PersonRepository
import repository.RoleRepository
import repository.UserRepository
import service.PersonService
import service.RoleService
import service.UserService

class UserView {
    @Composable
    @Preview
    fun UserRequests(
        viewRequests: Map<String, Boolean>,
        setViewRequests: (Map<String, Boolean>) -> Unit,
        entityManager: EntityManager
    ) {
        val userService = UserService(UserRepository(entityManager))
        val personService = PersonService(PersonRepository(entityManager))
        val roleService = RoleService(RoleRepository(entityManager))

        var userDetails by remember { mutableStateOf(
            mapOf(
                "id" to "",
                "person_id" to "",
                "role_id" to "",
                "username" to "",
                "password" to ""
            )
        )}

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // BUTTONS
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // CREATE ENTITY
                Button(
                    onClick = {
                        println(
                            userService.createEntity(
                                User(
                                    role = roleService.getEntityById(userDetails["role_id"]!!).get(),
                                    person = personService.getEntityById(userDetails["person_id"]!!).get(),
                                    username = userDetails["username"]!!,
                                    password = userDetails["password"]!!
                                )
                            )
                        )
                    }
                ) {
                    Text("Create User")
                }

                // DELETE ENTITY BY ID
                Button(
                    onClick = {
                        if (userDetails["id"]!! != "") {
                            userService.deleteEntityById(userDetails["id"]!!)
                        }
                    }
                ) {
                    Text("Delete User by ID")
                }

                // GET ENTITY BY ID
                Button(
                    onClick = {
                        if (userDetails["id"]!! != "") {
                            userService.getEntityById(userDetails["id"]!!)
                        }
                    }
                ) {
                    Text("Get User by ID")
                }

                // GET ALL ENTITIES
                Button(
                    onClick = {
                        println(userService.getAllEntities())
                    }
                ) {
                    Text("Get Users")
                }

                // BACK
                Button(
                    onClick = {
                        setViewRequests(viewRequests.plus("user" to false))
                    }
                ) {
                    Text("Back")
                }
            }

            // TEXT FIELDS
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ID
                TextField(
                    value = userDetails["id"]!!,
                    onValueChange = { userDetails = userDetails.plus("id" to it) },
                    label = { Text("ID") }
                )

                // PERSON ID
                TextField(
                    value = userDetails["person_id"]!!,
                    onValueChange = { userDetails = userDetails.plus("person_id" to it) },
                    label = { Text("Person ID") }
                )

                // ROLE ID
                TextField(
                    value = userDetails["role_id"]!!,
                    onValueChange = { userDetails = userDetails.plus("role_id" to it) },
                    label = { Text("Role ID") }
                )

                // USERNAME
                TextField(
                    value = userDetails["username"]!!,
                    onValueChange = { userDetails = userDetails.plus("username" to it) },
                    label = { Text("Username") }
                )

                // PASSWORD
                TextField(
                    value = userDetails["password"]!!,
                    onValueChange = { userDetails = userDetails.plus("password" to it) },
                    label = { Text("Password") }
                )
            }
        }
    }
}