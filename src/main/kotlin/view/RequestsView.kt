package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jakarta.persistence.Persistence

class RequestsView {
    private val entityManagerFactory = Persistence.createEntityManagerFactory("MyAppPersistenceUnit")
    private val entityManager = entityManagerFactory.createEntityManager()

    @Composable
    @Preview
    fun view() {
        val (viewRequests, setViewRequests) = remember { mutableStateOf(
            mapOf(
                "person" to false,
                "product_category" to false,
                "product" to false,
                "role" to false,
                "sale_item" to false,
                "sale" to false,
                "supplier" to false,
                "transaction" to false,
                "transaction_type" to false,
                "user" to false
            )
        )}


        if(viewRequests["person"]!!) {
            PersonView().PersonRequests(viewRequests, setViewRequests, entityManager)
        } else if(viewRequests["product"]!!) {
            ProductView().ProductRequests(viewRequests, setViewRequests, entityManager)
        } else if(viewRequests["product_category"]!!) {
            ProductCategoryView().ProductCategoryRequests(viewRequests, setViewRequests, entityManager)
        } else if(viewRequests["role"]!!) {
            RoleView().RoleRequests(viewRequests, setViewRequests, entityManager)
        } else if(viewRequests["sale_item"]!!) {
            SaleItemView().SaleItemRequests(viewRequests, setViewRequests, entityManager)
        } else if (viewRequests["supplier"]!!) {
            SupplierView().SupplierRequests(viewRequests, setViewRequests, entityManager)
        } else if(viewRequests["user"]!!) {
            UserView().UserRequests(viewRequests, setViewRequests, entityManager)
        } else {
            MainMenu(viewRequests, setViewRequests)
        }
    }

    @Composable
    @Preview
    fun MainMenu(
        viewRequests: Map<String, Boolean>,
        setViewRequests: (Map<String, Boolean>) -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // PERSON VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("person" to true))
                }
            ) {
                Text("Person Requests")
            }

            // PRODUCT CATEGORY VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("product_category" to true))
                }
            ) {
                Text("Product Category Requests")
            }

            // PRODUCT VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("product" to true))
                }
            ) {
                Text("Product Requests")
            }

            // ROLE VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("role" to true))
                }
            ) {
                Text("Role Requests")
            }

            // SALE ITEM VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("sale_item" to true))
                }
            ) {
                Text("Sale Item Requests")
            }

            // SALE VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("sale" to true))
                }
            ) {
                Text("Sale Requests")
            }

            // SUPPLIER VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("supplier" to true))
                }
            ) {
                Text("Supplier Requests")
            }

            // TRANSACTION VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("transaction" to true))
                }
            ) {
                Text("Transaction Requests")
            }

            // TRANSACTION TYPE VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("transaction_type" to true))
                }
            ) {
                Text("Transaction Type Requests")
            }

            // USER VIEW
            Button(
                onClick = {
                    setViewRequests(viewRequests.plus("user" to true))
                }
            ) {
                Text("User Requests")
            }
        }
    }
}