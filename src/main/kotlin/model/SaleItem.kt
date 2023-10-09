package model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tbl_sale_items")
data class SaleItem(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    val sale: Sale,

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    val product: Product,

    val quantity: Int,

    @Column(name = "unit_price")
    val unitPrice: Double,
    val subtotal: Double,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant
) {
    constructor(
        sale: Sale,
        product: Product,
        quantity: Int,
        unitPrice: Double,
        subtotal: Double
    ): this(
        UUID.randomUUID().toString(),
        sale,
        product,
        quantity,
        unitPrice,
        subtotal,
        Instant.now(),
        Instant.now()
    )

    constructor(): this(
        "",
        Sale(),
        Product(),
        0,
        0.0,
        0.0,
        Instant.now(),
        Instant.now()
    )
}