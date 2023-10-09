package service.base

import repository.base.EntityRepositoryImpl
import java.io.Serializable
import java.util.*

interface EntityService<T, ID: Serializable> {
    fun getAllEntities(): List<T>
    fun getEntityById(id: ID): Optional<T>
    fun createEntity(entity: T): T
    fun deleteEntityById(id: ID)
}

abstract class EntityServiceImpl <T: Any, ID: Serializable> (
    private val entityRepository: EntityRepositoryImpl<T, ID>
): EntityService<T, ID> {
    override fun getAllEntities(): List<T> {
        return entityRepository.findAll()
    }

    override fun getEntityById(id: ID): Optional<T> {
        return entityRepository.findById(id)
    }

    override fun createEntity(entity: T): T {
        return entityRepository.save(entity)
    }

    override fun deleteEntityById(id: ID) {
        entityRepository.delete(id)
    }
}