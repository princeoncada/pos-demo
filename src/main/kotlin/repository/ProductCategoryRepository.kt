package repository

import jakarta.persistence.EntityManager
import model.ProductCategory

class ProductCategoryRepository(
    entityManager: EntityManager
): GenericRepositoryImpl<ProductCategory, String>(entityManager) {
    override fun getEntityClass(): Class<ProductCategory> {
        return ProductCategory::class.java
    }

    fun findByName(name: String): ProductCategory? {
        val properties = mapOf("name" to name)
        val productCategories = findByProperties(properties)
        return if (productCategories.isEmpty()) null else productCategories[0]
    }
}
