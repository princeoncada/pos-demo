package model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tbl_sales")
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,

    @Column(name = "sale_date")
    val saleDate: LocalDateTime,

    @Column(name = "total_amount")
    val totalAmount: Double,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant
) {
    constructor(
        user: User,
        saleDate: LocalDateTime,
        totalAmount: Double,
        createdAt: Instant,
        updatedAt: Instant
    ): this(
        UUID.randomUUID().toString(),
        user,
        saleDate,
        totalAmount,
        Instant.now(),
        Instant.now()
    )

    constructor() : this(
        "",
        User(),
        LocalDateTime.now(),
        0.0,
        Instant.now(),
        Instant.now()
    )
}