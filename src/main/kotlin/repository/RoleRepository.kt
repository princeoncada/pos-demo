package repository

import jakarta.persistence.EntityManager
import model.Role
import repository.base.EntityRepositoryImpl

class RoleRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<Role, String>(entityManager) {
    override fun getEntityClass(): Class<Role> {
        return Role::class.java
    }
}