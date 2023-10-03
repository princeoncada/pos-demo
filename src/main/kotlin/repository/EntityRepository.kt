package repository

import java.util.*

interface EntityRepository<Entity, IdType> {
    fun findById(id: IdType?): Optional<Entity>
    fun findAll(): List<Entity>
    fun save(entity: Entity): Entity
    fun delete(entity: Entity)
}