package com.divar.spring.core.location.service

import com.divar.spring.core.location.entity.Province
import com.divar.spring.core.location.repository.ProvinceRepository
import org.springframework.stereotype.Service

@Service
class ProvinceService(
    val repository: ProvinceRepository
) {
    fun findAll(): List<Province> {
        return repository.findAll()
    }
}