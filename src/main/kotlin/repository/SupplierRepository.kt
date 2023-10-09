package repository

import jakarta.persistence.EntityManager
import model.Supplier
import repository.base.EntityRepositoryImpl

class SupplierRepository(
    entityManager: EntityManager
): EntityRepositoryImpl<Supplier, String>(entityManager){
    override fun getEntityClass(): Class<Supplier> {
        return Supplier::class.java
    }
}