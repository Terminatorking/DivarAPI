package com.divar.spring.core.location.dto

import com.divar.spring.core.location.entity.Province

data class ProvinceResponse(
    val id: Long? = null,
    val name: String,
    val cities: List<CityResponse>? = null
)

fun Province.toProvinceResponse(
    includeCities: Boolean = true,
    includeNeighbourhoods: Boolean = true,
): ProvinceResponse {
    return ProvinceResponse(
        id = this.id,
        name = this.name,
        cities = if (includeCities)
            this.cities?.map {
                it.toCityResponse(includeNeighbourhoods = includeNeighbourhoods)
            } else null
    )
}