package com.divar.spring.core.location.dto

import com.divar.spring.core.location.entity.City

data class CityResponse(
    val id: Long?,
    val name: String,
    val neighbourhoods: List<NeighbourhoodResponse>? = null
)

fun City.toCityResponse(isFillNeighbourhoods: Boolean = true): CityResponse {
    return CityResponse(
        id = this.id,
        name = this.name,
        neighbourhoods = if (isFillNeighbourhoods) this.neighborhoods?.map { it.toNeighbourhoodResponse() } else null,
    )
}