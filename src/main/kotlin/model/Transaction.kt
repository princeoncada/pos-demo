package model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tbl_transactions")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "transaction_type_id")
    val transactionType: TransactionType,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,
    val quantity: Int,

    @Column(name = "transaction_date")
    val transactionDate: LocalDateTime,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant
) {
    constructor(
        user: User,
        transactionType: TransactionType,
        product: Product,
        quantity: Int,
        transactionDate: LocalDateTime
    ): this(
        UUID.randomUUID().toString(),
        user,
        transactionType,
        product,
        quantity,
        transactionDate,
        Instant.now(),
        Instant.now()
    )

    constructor(): this(
        "",
        User(),
        TransactionType(),
        Product(),
        0,
        LocalDateTime.now(),
        Instant.now(),
        Instant.now()
    )
}