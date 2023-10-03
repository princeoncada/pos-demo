package service

import java.util.*

interface EntityService<Entity, IdType> {
    fun getAllEntities(): List<Entity>
    fun getEntityById(id: IdType): Optional<Entity>
    fun createEntity(entity: Entity): Entity
    fun deleteEntity(entity: Entity)
}