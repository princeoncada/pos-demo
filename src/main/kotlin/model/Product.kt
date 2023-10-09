package model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tbl_products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    val name: String,

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    val category: ProductCategory,

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    val supplier: Supplier,

    val description: String,
    val price: Double,

    @Column(name = "cost_price")
    val costPrice: Double,
    val barcode: String,

    @Column(name = "stock_quantity")
    val stockQuantity: Int,
    val image: String,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant
) {
    constructor(
        name: String,
        category: ProductCategory,
        supplier: Supplier,
        description: String,
        price: Double,
        costPrice: Double,
        barcode: String,
        stockQuantity: Int,
        image: String
    ): this(
        UUID.randomUUID().toString(),
        name,
        category,
        supplier,
        description,
        price,
        costPrice,
        barcode,
        stockQuantity,
        image,
        Instant.now(),
        Instant.now()
    )

    constructor(): this(
        "",
        "",
        ProductCategory(),
        Supplier(),
        "",
        0.0,
        0.0,
        "",
        0,
        "",
        Instant.now(),
        Instant.now()
    )
}