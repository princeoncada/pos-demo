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
import model.ProductCategory
import repository.ProductCategoryRepository
import service.ProductCategoryService

class ProductCategoryView{
    @Composable
    @Preview
    fun ProductCategoryRequests(
        viewRequests: Map<String, Boolean>,
        setViewRequests: (Map<String, Boolean>) -> Unit,
        entityManager: EntityManager
    ) {
        val productCategoryService = ProductCategoryService(ProductCategoryRepository(entityManager))
        var productCategoryDetails by remember { mutableStateOf(mapOf(
            "id" to "",
            "name" to "",
            "description" to ""
        )) }

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) { // START ROW BODY

                // BUTTONS
                Column (
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { // START COLUMN BODY
                    // BTN CREATE PRODUCT CATEGORY
                    Button(
                        onClick = {
                            println(
                                productCategoryService.createEntity(
                                    ProductCategory(
                                        name = productCategoryDetails["name"]!!,
                                        description = productCategoryDetails["description"]!!
                                    )
                                )
                            )
                        }
                    ) {
                        Text("Create Product Category")
                    }

                    // BTN DELETE BY ID
                    Button(
                        onClick = {
                            if (productCategoryDetails["id"]!! != "") {
                                productCategoryService.deleteEntityById(productCategoryDetails["id"]!!)
                            }
                        }
                    ) {
                        Text("Delete Product Category")
                    }

                    // BTN GET ALL
                    Button(
                        onClick = {
                            println(productCategoryService.getAllEntities())
                        }
                    ) {
                        Text("Get All Product Categories")
                    }

                    // BTN GET BY ID
                    Button(
                        onClick = {
                            if (productCategoryDetails["id"]!! != "") {
                                println(productCategoryService.getEntityById(productCategoryDetails["id"]!!))
                            }
                        }
                    ) {
                        Text("Get Product Category by ID")
                    }

                    // BACK
                    Button(
                        onClick = {
                            setViewRequests(viewRequests.plus("product_category" to false))
                        }
                    ) {
                        Text("Back")
                    }
                } // END COLUMN BODY

                // TEXT FIELDS
                Column (
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { // START COLUMN BODY
                    // ID
                    TextField(
                        value = productCategoryDetails["id"]!!,
                        onValueChange = {
                            productCategoryDetails = productCategoryDetails.plus("id" to it)
                        },
                        label = { Text("ID") }
                    )

                    // NAME
                    TextField(
                        value = productCategoryDetails["name"]!!,
                        onValueChange = {
                            productCategoryDetails = productCategoryDetails.plus("name" to it)
                        },
                        label = { Text("Name") }
                    )

                    // DESCRIPTION
                    TextField(
                        value = productCategoryDetails["description"]!!,
                        onValueChange = {
                            productCategoryDetails = productCategoryDetails.plus("description" to it)
                        },
                        label = { Text("Description") }
                    )
                } // END COLUMN BODY

            } // END ROW BODY
        }
    }
}