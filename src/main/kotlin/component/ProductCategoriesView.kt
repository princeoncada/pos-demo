package component

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
import model.ProductCategory
import repository.ProductCategoryRepository

class ProductCategoriesView(
    private val productCategoryRepository: ProductCategoryRepository,
) {
    @Composable
    @Preview
    fun ProductCategoriesRequests(
        viewRequests: Map<String, Boolean>,
        setViewRequests: (Map<String, Boolean>) -> Unit
    ) {
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
            ) {

                // Buttons
                Column (
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // btn create product category
                    Button(
                        onClick = {
                            productCategoryRepository.save(
                                ProductCategory(
                                    name = productCategoryDetails["name"]!!,
                                    description = productCategoryDetails["description"]!!
                                )
                            )
                        }
                    ) {
                        Text("Create Product Category")
                    }

                    // btn delete product category
                    Button(
                        onClick = {
                            productCategoryRepository.delete(productCategoryDetails["id"]!!)
                        }
                    ) {
                        Text("Delete Product Category")
                    }

                    // btn get all
                    Button(
                        onClick = {
                            productCategoryRepository.findAll().forEach {
                                println(it)
                            }
                        }
                    ) {
                        Text("Get All Product Categories")
                    }

                    // btn get by name
                    Button(
                        onClick = {
                            productCategoryRepository.findByName(productCategoryDetails["name"]!!)?.let {
                                println(it)
                            }
                        }
                    ) {
                        Text("Get Product Category by Name")
                    }

                    // back
                    Button(
                        onClick = {
                            setViewRequests(viewRequests.plus("product" to false))
                        }
                    ) {
                        Text("Back")
                    }
                }

                // Text Fields
                Column (
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // id
                    TextField(
                        value = productCategoryDetails["id"]!!,
                        onValueChange = {
                            productCategoryDetails = productCategoryDetails.plus("id" to it)
                            println(productCategoryDetails["id"]!!)
                                        },
                        label = { Text("ID") }
                    )

                    // name
                    TextField(
                        value = productCategoryDetails["name"]!!,
                        onValueChange = {
                            productCategoryDetails = productCategoryDetails.plus("name" to it)
                            println(productCategoryDetails["name"]!!)
                                        },
                        label = { Text("Name") }
                    )

                    // description
                    TextField(
                        value = productCategoryDetails["description"]!!,
                        onValueChange = {
                            productCategoryDetails = productCategoryDetails.plus("description" to it)
                            println(productCategoryDetails["description"]!!)
                                        },
                        label = { Text("Description") }
                    )
                }
            }
        }
    }
}