package model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tbl_suppliers")
data class Supplier(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    val person: Person,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: Instant
) {
    constructor(
        person: Person
    ): this(
        UUID.randomUUID().toString(),
        person,
        Instant.now(),
        Instant.now()
    )

    constructor(): this(
        "",
        Person(),
        Instant.now(),
        Instant.now()
    )
}