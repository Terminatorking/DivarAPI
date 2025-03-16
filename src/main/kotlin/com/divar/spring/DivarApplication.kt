package com.divar.spring

import com.divar.spring.core.location.service.CityService
import com.divar.spring.core.location.service.NeighbourhoodService
import com.divar.spring.core.location.service.ProvinceService
import com.divar.spring.utils.provider.LocationDataProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class DivarApplication

fun main(args: Array<String>) {
    val context = runApplication<DivarApplication>(*args)
    initLocations(context)
}

fun initLocations(context: ConfigurableApplicationContext) {
    val provinceService = context.getBean(ProvinceService::class.java)
    val cityService = context.getBean(CityService::class.java)
    val neighbourhoodService = context.getBean(NeighbourhoodService::class.java)
    val triple = LocationDataProvider.getData()
    if (neighbourhoodService.count() < 1) {
        provinceService.saveAll(triple.first)
        cityService.saveAll(triple.second)
        neighbourhoodService.saveAll(triple.third)
    }
}
