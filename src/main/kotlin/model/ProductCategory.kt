package model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tbl_product_categories")
data class ProductCategory (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    val name: String,
    val description: String,

    @Column(name = "created_at")
    val createdAt: Instant,

    @Column(name = "updated_at")
    val updatedAt: Instant
) {
    constructor(
        name: String,
        description: String,
    ): this(
        UUID.randomUUID().toString(),
        name,
        description,
        Instant.now(),
        Instant.now()
    )

    constructor() : this(
        "",
        "",
        "",
        Instant.now(),
        Instant.now()
    )

}