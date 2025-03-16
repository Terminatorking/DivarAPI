package com.divar.spring.core.location.dto

import com.divar.spring.core.location.entity.NeighbourHood

data class NeighbourhoodResponse(val id: Long?, val name: String)

fun NeighbourHood.toNeighbourhoodResponse(): NeighbourhoodResponse {
    return NeighbourhoodResponse(id = this.id, name = this.name)
}