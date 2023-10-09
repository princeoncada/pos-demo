package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import component.Input
import jakarta.persistence.EntityManager
import model.SaleItem
import repository.ProductRepository
import repository.SaleItemRepository
import repository.SaleRepository
import service.ProductService
import service.SaleItemService
import service.SaleService

class SaleItemView {
    @Composable
    @Preview
    fun SaleItemRequests(
        viewRequests: Map<String, Boolean>,
        setViewRequests: (Map<String, Boolean>) -> Unit,
        entityManager: EntityManager
    ) {
        val saleItemService = SaleItemService(SaleItemRepository(entityManager))
        val saleService = SaleService(SaleRepository(entityManager))
        val productService = ProductService(ProductRepository(entityManager))

        val (saleItemOptions, setSaleItemOptions) = remember { mutableStateOf(saleItemService.getAllEntities().map { it.id }) }
        val (saleOptions, setSaleOptions) = remember { mutableStateOf(saleService.getAllEntities().map { it.id }) }
        val (productOptions, setProductOptions) = remember { mutableStateOf(productService.getAllEntities().map { it.id }) }

        val (saleItemDetails, setSaleItemDetails) = remember {
            mutableStateOf(mapOf(
                "id" to "",
                "sale_id" to "",
                "product_id" to "",
                "quantity" to "",
                "unit_price" to "",
                "subtotal" to ""
        ))}

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
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
                        try {
                            saleItemService.createEntity(
                                SaleItem(
                                    sale = saleService.getEntityById(saleItemDetails["sale_id"]!!).get(),
                                    product = productService.getEntityById(saleItemDetails["product_id"]!!).get(),
                                    quantity = saleItemDetails["quantity"]!!.toInt(),
                                    unitPrice = saleItemDetails["unit_price"]!!.toDouble(),
                                    subtotal = saleItemDetails["subtotal"]!!.toDouble()
                                )
                            )
                            println("Successfully created new SaleItem!")
                        } catch(e: Exception) {
                            println(e.message)
                        } finally {
                            setSaleItemOptions(saleItemService.getAllEntities().map { it.id })
                            setSaleOptions(saleService.getAllEntities().map { it.id })
                            setProductOptions(productService.getAllEntities().map { it.id })

                            setSaleItemDetails(saleItemDetails.plus("sale_id" to ""))
                            setSaleItemDetails(saleItemDetails.plus("quantity" to ""))
                            setSaleItemDetails(saleItemDetails.plus("unit_price" to ""))
                            setSaleItemDetails(saleItemDetails.plus("subtotal" to ""))
                        }
                    }
                ) {
                    Text("Create SaleItem")
                }

                // DELETE BY ID BUTTON
                Button(
                    onClick = {
                        try {
                            saleItemService.deleteEntityById(saleItemDetails["id"]!!)
                            println("SaleItem with ID ${saleItemDetails["id"]} deleted")
                        } catch (e: Exception) {
                            println(e.message)
                        } finally {
                            setSaleItemOptions(saleItemService.getAllEntities().map { it.id })
                            setSaleOptions(saleService.getAllEntities().map { it.id })
                            setProductOptions(productService.getAllEntities().map { it.id })

                            setSaleItemDetails(saleItemDetails.plus("id" to ""))
                        }
                    }
                ) {
                    Text("Delete SaleItem by ID")
                }

                // GET BY ID BUTTON
                Button(
                    onClick = {
                        try {
                            val saleItem = saleItemService.getEntityById(saleItemDetails["id"]!!)
                            print(saleItem.get())
                        } catch (e: Exception) {
                            println(e.message)
                        } finally {
                            setSaleItemDetails(saleItemDetails.plus("id" to ""))
                        }
                    }
                ) {
                    Text("Get SaleItem by ID")
                }

                // GET ALL BUTTON
                Button(
                    onClick = {
                        try {
                            val saleItems = saleItemService.getAllEntities()
                            println(saleItems)
                        } catch (e: Exception) {
                            println(e.message)
                        }
                    }
                ) {
                    Text("Get SaleItems")
                }

                // BACK BUTTON
                Button(
                    onClick = {
                        setViewRequests(viewRequests.plus("sale_item" to false))
                        setSaleItemDetails(mapOf(
                            "id" to "",
                            "sale_id" to "",
                            "product_id" to "",
                            "quantity" to "",
                            "unit_price" to "",
                            "subtotal" to ""
                        ))
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
                Input().Dropdown(
                    name = "ID",
                    property = "id",
                    options = saleItemOptions,
                    details = saleItemDetails,
                    setDetails = setSaleItemDetails
                )

                // SALE ID
                Input().Dropdown(
                    name = "SALE ID",
                    property = "sale_id",
                    options = saleOptions,
                    details = saleItemDetails,
                    setDetails = setSaleItemDetails
                )

                // PRODUCT ID
                Input().Dropdown(
                    name = "PRODUCT ID",
                    property = "product_id",
                    options = productOptions,
                    details = saleItemDetails,
                    setDetails = setSaleItemDetails
                )

                // QUANTITY
                Input().IntegerField(
                    name = "QUANTITY",
                    property = "quantity",
                    details = saleItemDetails,
                    setDetails = setSaleItemDetails
                )

                // UNIT_PRICE
                Input().DoubleField(
                    name = "UNIT PRICE",
                    property = "unit_price",
                    details = saleItemDetails,
                    setDetails = setSaleItemDetails
                )

                // SUBTOTAL
                Input().DoubleField(
                    name = "SUBTOTAL",
                    property = "subtotal",
                    details = saleItemDetails,
                    setDetails = setSaleItemDetails
                )
            }
        }
    }
}