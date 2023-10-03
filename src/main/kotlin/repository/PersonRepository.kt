package repository

import model.Person
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Timestamp
import java.util.*

class PersonRepository(
    private val connection: Connection
): EntityRepository<Person, String> {
    override fun findById(id: String?): Optional<Person> {
        val statement = connection.prepareStatement("SELECT * FROM tbl_persons WHERE id = ?")
        statement.setString(1, id)
        val resultSet = statement.executeQuery()
        return if (resultSet.next()) {
            Optional.of(createPerson(resultSet))
        } else {
            Optional.empty()
        }
    }

    override fun findAll(): List<Person> {
        val statement = connection.prepareStatement("SELECT * FROM tbl_persons")
        val resultSet = statement.executeQuery()
        val persons = mutableListOf<Person>()
        while (resultSet.next()) {
            persons.add(createPerson(resultSet))
        }
        return persons
    }

    override fun delete(entity: Person) {
        val statement = connection.prepareStatement("DELETE FROM tbl_persons WHERE id = ?")
        statement.setString(1, entity.id)
        statement.executeUpdate()
    }

    override fun save(entity: Person): Person {
        val statement = connection.prepareStatement(
            """
                INSERT INTO tbl_persons (id, first_name, last_name, email, phone, address, created_at, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """.trimIndent()
        )
        statement.setString(1, entity.id)
        statement.setString(2, entity.firstName)
        statement.setString(3, entity.lastName)
        statement.setString(4, entity.email)
        statement.setString(5, entity.phone)
        statement.setString(6, entity.address)
        statement.setTimestamp(7, Timestamp.from(entity.createdAt))
        statement.setTimestamp(8, Timestamp.from(entity.updatedAt))
        statement.executeUpdate()
        return entity
    }

    private fun createPerson(result: ResultSet): Person {
        return Person(
            id = result.getString("id"),
            firstName = result.getString("first_name"),
            lastName = result.getString("last_name"),
            email = result.getString("email"),
            phone = result.getString("phone"),
            address = result.getString("address"),
            createdAt = result.getTimestamp("created_at").toInstant(),
            updatedAt = result.getTimestamp("updated_at").toInstant()
        )
    }
}