package repository

import jakarta.persistence.EntityManager
import model.SaleItem
import repository.base.EntityRepositoryImpl

class SaleItemRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<SaleItem, String>(entityManager) {
    override fun getEntityClass(): Class<SaleItem> {
        return SaleItem::class.java
    }
}