package com.divar.spring.core.location.repository

import com.divar.spring.core.location.entity.NeighbourHood
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NeighbourhoodRepository : JpaRepository<NeighbourHood, Long>