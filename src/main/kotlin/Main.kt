
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import component.ProductCategoriesView
import component.RequestsView
import jakarta.persistence.Persistence
import repository.PersonRepository
import repository.ProductCategoryRepository
import service.PersonService
import java.sql.Connection
import java.sql.DriverManager

@Composable
@Preview
fun App() {
    val entityManagerFactory = Persistence.createEntityManagerFactory("MyAppPersistenceUnit")
    val entityManager = entityManagerFactory.createEntityManager()

    RequestsView(
        PersonService(
            PersonRepository(
                establishDatabaseConnection()
            )
        ),
        ProductCategoriesView(
            ProductCategoryRepository(
                entityManager
            )
        )
    ).view()
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}


fun establishDatabaseConnection(): Connection {
    val url = "jdbc:mysql://localhost:8000/db_baste"
    val user = "user"
    val pass = "pass"
    return DriverManager.getConnection(url, user, pass)
}
