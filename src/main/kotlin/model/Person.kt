package model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tbl_persons")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String?,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,
    val email: String,
    val phone: String,
    val address: String,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at")
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
        ): this(
            UUID.randomUUID().toString(),
            firstName,
            lastName,
            email,
            phone,
            address,
            createdAt,
            updatedAt
    )

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