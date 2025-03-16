package com.divar.spring.core.location.controller

import com.divar.spring.core.location.dto.ProvinceResponse
import com.divar.spring.core.location.dto.toProvinceResponse
import com.divar.spring.core.location.service.ProvinceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/")
class LocationController(val service: ProvinceService) {

    @GetMapping("location")
    fun getLocations(): List<ProvinceResponse> {
        return service.findAll().map { it.toProvinceResponse() }
    }

}