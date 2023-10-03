package model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tbl_persons")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val address: String,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    constructor(
            firstName: String,
            lastName: String,
            email: String,
            phone: String,
            address: String,
            createdAt: Instant,
            updatedAt: Instant
        ): this(UUID.randomUUID().toString(), firstName, lastName, email, phone, address, createdAt, updatedAt)

    constructor() : this(
        null,
        "",
        "",
        "",
        "",
        "",
        Instant.now(),
        Instant.now()
    )
}