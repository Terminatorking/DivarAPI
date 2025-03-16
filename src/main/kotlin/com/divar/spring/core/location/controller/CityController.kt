package com.divar.spring.core.location.controller

import com.divar.spring.core.location.dto.CityResponse
import com.divar.spring.core.location.dto.toCityResponse
import com.divar.spring.core.location.service.CityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CityController(val service: CityService) {

    @GetMapping("api/v1/city")
    fun getCities(): List<CityResponse> {
        return service.findAll().map { it.toCityResponse(false) }
    }

}