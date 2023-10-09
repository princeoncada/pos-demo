package model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tbl_transaction_types")
data class TransactionType(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    val type: String,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant
) {
    constructor(
        type: String
    ): this(
        UUID.randomUUID().toString(),
        type,
        Instant.now(),
        Instant.now()
    )

    constructor(): this(
        "",
        "",
        Instant.now(),
        Instant.now()
    )
}