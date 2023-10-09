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
import model.Product
import repository.ProductCategoryRepository
import repository.ProductRepository
import repository.SupplierRepository
import service.ProductCategoryService
import service.ProductService
import service.SupplierService

class ProductView {
    @Composable
    @Preview
    fun ProductRequests(
        viewRequests: Map<String, Boolean>,
        setViewRequests: (Map<String, Boolean>) -> Unit,
        entityManager: EntityManager
    ) {
        val productService = ProductService(ProductRepository(entityManager))
        val productCategoryService = ProductCategoryService(ProductCategoryRepository(entityManager))
        val supplierService = SupplierService(SupplierRepository(entityManager))

        var productDetails by remember { mutableStateOf(
            mapOf(
                "id" to "",
                "name" to "",
                "category_id" to "",
                "supplier_id" to "",
                "description" to "",
                "price" to "",
                "cost_price" to "",
                "barcode" to "",
                "stock_quantity" to "",
                "image" to ""
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
                // CREATE PRODUCT BUTTON
                Button(
                    onClick = {
                        try {
                            println(productService.createEntity(
                                Product(
                                    name = productDetails["name"]!!,
                                    category = productCategoryService.getEntityById(productDetails["category_id"]!!).get(),
                                    supplier = supplierService.getEntityById(productDetails["supplier_id"]!!).get(),
                                    description = productDetails["description"]!!,
                                    price = productDetails["price"]!!.toDouble(),
                                    costPrice = productDetails["cost_price"]!!.toDouble(),
                                    barcode = productDetails["barcode"]!!,
                                    stockQuantity = productDetails["stock_quantity"]!!.toInt(),
                                    image = productDetails["image"]!!
                                )
                            ))
                        } catch (e: Exception) {
                            println(e.message)
                        }

                    }
                ) {
                    Text("Create Product")
                }

                // DELETE PRODUCT BY ID BUTTON
                Button(
                    onClick = {
                        if (productDetails["id"] != "") {
                            productService.deleteEntityById(productDetails["id"]!!)
                        }
                    }
                ) {
                    Text("Delete Product by ID")
                }

                // GET PRODUCT BY ID BUTTON
                Button(
                    onClick = {
                        if (productDetails["id"] != "") {
                            println(productService.getEntityById(productDetails["id"]!!).get())
                        }
                    }
                ) {
                    Text("Get Product by ID")
                }

                // GET ALL PRODUCTS BUTTON
                Button(
                    onClick = { println(productService.getAllEntities()) }
                ) {
                    Text("Get Products")
                }

                // BACK BUTTON
                Button(
                    onClick = {
                        setViewRequests(viewRequests.plus("product" to false))
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
                    value = productDetails["id"]!!,
                    onValueChange = { productDetails = productDetails.plus("id" to it) },
                    label = { Text("ID") }
                )

                // NAME
                TextField(
                    value = productDetails["name"]!!,
                    onValueChange = { productDetails = productDetails.plus("name" to it) },
                    label = { Text("Name") }
                )

                // CATEGORY ID
                TextField(
                    value = productDetails["category_id"]!!,
                    onValueChange = { productDetails = productDetails.plus("category_id" to it) },
                    label = { Text("Category ID") }
                )

                // SUPPLIER ID
                TextField(
                    value = productDetails["supplier_id"]!!,
                    onValueChange = { productDetails = productDetails.plus("supplier_id" to it) },
                    label = { Text("Supplier ID") }
                )

                // DESCRIPTION
                TextField(
                    value = productDetails["description"]!!,
                    onValueChange = { productDetails = productDetails.plus("description" to it) },
                    label = { Text("Description") }
                )

                // PRICE
                TextField(
                    value = productDetails["price"]!!,
                    onValueChange = { productDetails = productDetails.plus("price" to it) },
                    label = { Text("Price") }
                )

                // COST PRICE
                TextField(
                    value = productDetails["cost_price"]!!,
                    onValueChange = { productDetails = productDetails.plus("cost_price" to it) },
                    label = { Text("Cost Price") }
                )

                // BARCODE
                TextField(
                    value = productDetails["barcode"]!!,
                    onValueChange = { productDetails = productDetails.plus("barcode" to it) },
                    label = { Text("Barcode") }
                )

                // STOCK QUANTITY
                TextField(
                    value = productDetails["stock_quantity"]!!,
                    onValueChange = { productDetails = productDetails.plus("stock_quantity" to it) },
                    label = { Text("Stock Quantity") }
                )

                // IMAGE URL
                TextField(
                    value = productDetails["image"]!!,
                    onValueChange = { productDetails = productDetails.plus("image" to it) },
                    label = { Text("Image URL") }
                )
            }
        }
    }
}