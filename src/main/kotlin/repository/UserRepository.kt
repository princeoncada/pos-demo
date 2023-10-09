package repository

import jakarta.persistence.EntityManager
import model.User
import repository.base.EntityRepositoryImpl

class UserRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<User, String>(entityManager) {
    override fun getEntityClass(): Class<User> {
        return User::class.java
    }
}