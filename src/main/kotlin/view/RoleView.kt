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
import model.Role
import repository.RoleRepository
import service.RoleService

class RoleView {
    @Composable
    @Preview
    fun RoleRequests(
        viewRequests: Map<String, Boolean>,
        setViewRequests: (Map<String, Boolean>) -> Unit,
        entityManager: EntityManager
    ) {
        val roleService = RoleService(RoleRepository(entityManager))
        var roleDetails by remember { mutableStateOf(mapOf(
            "id" to "",
            "name" to ""
        )) }

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
                Button (
                    onClick = {
                        println(
                            roleService.createEntity(
                                Role(
                                    name = roleDetails["name"]!!
                                )
                            )
                        )
                    }
                ) {
                    Text("Create Role")
                }

                // DELETE ENTITY BY ID
                Button(
                    onClick = {
                        if (roleDetails["id"]!! != "") {
                            roleService.deleteEntityById(roleDetails["id"]!!)
                        }
                    }
                ) {
                    Text("Delete Role by ID")
                }

                // GET ALL ENTITIES
                Button (
                    onClick = {
                        println(roleService.getAllEntities())
                    }
                ) {
                    Text("Get All Roles")
                }

                // GET ENTITY BY ID
                Button(
                    onClick = {
                        if (roleDetails["id"]!! != "") {
                            println(roleService.getEntityById(roleDetails["id"]!!))
                        }
                    }
                ) {
                    Text("Get Role by ID")
                }

                // BACK
                Button(
                    onClick = {
                        setViewRequests(viewRequests.plus("role" to false))
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
                    value = roleDetails["id"]!!,
                    onValueChange = {
                        roleDetails = roleDetails.plus("id" to it)
                    },
                    label = { Text("ID") }
                )

                // NAME
                TextField(
                    value = roleDetails["name"]!!,
                    onValueChange = {
                        roleDetails = roleDetails.plus("name" to it)
                    },
                    label = { Text("Name") }
                )
            }
        }
    }
}