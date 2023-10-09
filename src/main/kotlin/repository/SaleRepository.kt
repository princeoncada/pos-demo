package repository

import jakarta.persistence.EntityManager
import model.Sale
import repository.base.EntityRepositoryImpl

class SaleRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<Sale, String>(entityManager) {
    override fun getEntityClass(): Class<Sale> {
        return Sale::class.java
    }
}