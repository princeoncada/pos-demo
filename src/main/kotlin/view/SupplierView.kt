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
import model.Supplier
import repository.PersonRepository
import repository.SupplierRepository
import service.PersonService
import service.SupplierService

class SupplierView {
    @Composable
    @Preview
    fun SupplierRequests(
        viewRequests: Map<String, Boolean>,
        setViewRequests: (Map<String, Boolean>) -> Unit,
        entityManager: EntityManager
    ) {
        val supplierService = SupplierService(SupplierRepository(entityManager))
        val personService = PersonService(PersonRepository(entityManager))

        var supplierDetails by remember { mutableStateOf(mapOf(
            "id" to "",
            "person_id" to ""
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
                // CREATE BUTTON
                Button(
                    onClick = {
                        println(
                            supplierService.createEntity(
                                Supplier(
                                    person = personService.getEntityById(supplierDetails["person_id"]!!).get()
                                )
                            )
                        )
                    }
                ) {
                    Text("Create Supplier")
                }

                // DELETE BY ID BUTTON
                Button(
                    onClick = {
                        if (supplierDetails["id"]!! != "") {
                            supplierService.deleteEntityById(supplierDetails["id"]!!)
                        }
                    }
                ) {
                    Text("Delete Supplier By ID")
                }

                // GET ENTITY BY ID BUTTON
                Button(
                    onClick = {
                        if (supplierDetails["id"]!! != "") {
                            println(supplierService.getEntityById(supplierDetails["id"]!!))
                        }
                    }
                ) {
                    Text("Get Supplier By ID")
                }

                // GET ALL ENTITIES BUTTON
                Button(
                    onClick = {
                        println(supplierService.getAllEntities())
                    }
                ) {
                    Text("Get All Suppliers")
                }

                // BACK BUTTON
                Button(
                    onClick = {
                        setViewRequests(viewRequests.plus("supplier" to false))
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
                // ID TEXT FIELD
                TextField(
                    value = supplierDetails["id"]!!,
                    onValueChange = { supplierDetails = supplierDetails.plus("id" to it) },
                    label = { Text("ID") }
                )

                // PERSON_ID TEXT FIELD
                TextField(
                    value = supplierDetails["person_id"]!!,
                    onValueChange = { supplierDetails = supplierDetails.plus("person_id" to it) },
                    label = { Text("Person ID") }
                )
            }
        }
    }
}