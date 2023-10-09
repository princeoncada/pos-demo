package repository

import jakarta.persistence.EntityManager
import model.Product
import repository.base.EntityRepositoryImpl

class ProductRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<Product, String>(entityManager) {
    override fun getEntityClass(): Class<Product> {
        return Product::class.java
    }
}
