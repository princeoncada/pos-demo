package repository

import jakarta.persistence.EntityManager
import model.TransactionType
import repository.base.EntityRepositoryImpl

class TransactionTypeRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<TransactionType, String>(entityManager) {
    override fun getEntityClass(): Class<TransactionType> {
        return TransactionType::class.java
    }
}