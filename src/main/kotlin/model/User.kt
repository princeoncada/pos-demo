package model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tbl_users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    val person: Person,

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    val role: Role,

    val username: String,
    val password: String,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Instant
) {
    constructor(
        person: Person,
        role: Role,
        username: String,
        password: String,
    ): this(
        UUID.randomUUID().toString(),
        person,
        role,
        username,
        password,
        Instant.now(),
        Instant.now()
    )

    constructor() : this(
        "",
        Person(),
        Role(),
        "",
        "",
        Instant.now(),
        Instant.now()
    )
}