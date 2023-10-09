package model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "tbl_roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    val name: String,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant
) {
    constructor(
        name: String,
    ): this(
        java.util.UUID.randomUUID().toString(),
        name,
        Instant.now(),
        Instant.now()
    )

    constructor() : this(
        "",
        "",
        Instant.now(),
        Instant.now()
    )
}