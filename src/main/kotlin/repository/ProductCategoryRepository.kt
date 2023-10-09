package repository

import jakarta.persistence.EntityManager
import model.ProductCategory
import repository.base.EntityRepositoryImpl

class ProductCategoryRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<ProductCategory, String>(entityManager) {
    override fun getEntityClass(): Class<ProductCategory> {
        return ProductCategory::class.java
    }

    fun findByName(name: String): ProductCategory? {
        val properties = mapOf("name" to name)
        val productCategories = findByProperties(properties)
        return if (productCategories.isEmpty()) null else productCategories[0]
    }
}
