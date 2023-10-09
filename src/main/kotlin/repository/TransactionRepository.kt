package repository

import jakarta.persistence.EntityManager
import model.Transaction
import repository.base.EntityRepositoryImpl

class TransactionRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<Transaction, String>(entityManager) {
    override fun getEntityClass(): Class<Transaction> {
        return Transaction::class.java
    }
}