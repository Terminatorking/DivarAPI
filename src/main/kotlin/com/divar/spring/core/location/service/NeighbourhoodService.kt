package com.divar.spring.core.location.service

import com.divar.spring.core.location.entity.NeighbourHood
import com.divar.spring.core.location.repository.NeighbourhoodRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class NeighbourhoodService(
    val repository: NeighbourhoodRepository
) {

    fun findAll(): List<NeighbourHood> {
        return repository.findAll()
    }

    fun findById(id: Long): NeighbourHood? {
        return repository.findById(id).getOrNull()
    }

    fun getReferenceById(id: Long): NeighbourHood? {
        return try {
            repository.getReferenceById(id)
        } catch (ex: Exception) {
            null
        }
    }

    fun save(entity: NeighbourHood): NeighbourHood {
        return repository.save(entity)
    }

    fun saveAll(entity: MutableList<NeighbourHood>): MutableList<NeighbourHood> {
        return repository.saveAll(entity)
    }

    fun count(): Long {
        return repository.count()
    }
}