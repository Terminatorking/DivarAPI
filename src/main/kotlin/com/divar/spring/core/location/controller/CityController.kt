package com.divar.spring.core.location.controller

import com.divar.spring.core.location.dto.toCityResponse
import com.divar.spring.core.location.service.CityService
import com.divar.spring.utils.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/")
class CityController(val service: CityService) {

    @GetMapping("city")
    fun getCity(
        @RequestParam("includeNeighborhoods") includeNeighborhoods: Boolean? = false
    ): ResponseEntity<*> {
        return ApiResponse.success(
            service.findAll().map {
                it.toCityResponse(
                    includeNeighbourhoods = includeNeighborhoods ?: false
                )
            }
        )
    }
}