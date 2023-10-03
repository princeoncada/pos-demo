package repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.criteria.Predicate
import java.io.Serializable
import java.util.*

interface GenericRepository<T, ID : Serializable> {
    fun save(entity: T): T
    fun findById(id: ID): Optional<T>
    fun findByProperties(properties: Map<String, Any?>): List<T>
    fun findAll(): List<T>
    fun delete(id: ID)
}

abstract class GenericRepositoryImpl<T : Any, ID: Serializable>(
    @PersistenceContext
    protected val entityManager: EntityManager
): GenericRepository<T, ID> {
    override fun save(entity: T): T {
        entityManager.transaction.begin()
        val managedEntity = entityManager.merge(entity)
        entityManager.persist(managedEntity)
        entityManager.transaction.commit()
        return entity
    }

    override fun findById(id: ID): Optional<T> {
        return Optional.of(entityManager.find(getEntityClass(), id))
    }

    override fun findByProperties(properties: Map<String, Any?>): List<T> {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(getEntityClass())
        val root = criteriaQuery.from(getEntityClass())
        val predicates = mutableListOf<Predicate>()
        properties.forEach { (key, value) ->
            if (value != null) { predicates.add(criteriaBuilder.equal(root.get<Any>(key), value)) }
        }
        criteriaQuery.where(*predicates.toTypedArray())
        return entityManager.createQuery(criteriaQuery).resultList
    }

    override fun findAll(): List<T> {
        val criteriaQuery = entityManager.criteriaBuilder.createQuery(getEntityClass())
        criteriaQuery.select(criteriaQuery.from(getEntityClass()))
        return entityManager.createQuery(criteriaQuery).resultList
    }

    override fun delete(id: ID) {
        entityManager.transaction.begin()
        val entity = findById(id).get()
        entityManager.remove(entity)
        entityManager.transaction.commit()
    }

    abstract fun getEntityClass(): Class<T>
}