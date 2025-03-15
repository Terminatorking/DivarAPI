package com.divar.spring.core.location.controller

import com.divar.spring.core.location.entity.Province
import com.divar.spring.core.location.service.ProvinceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProvinceController(val service: ProvinceService) {

    @GetMapping("api/v1/province")
    fun getProvinces(): List<Province> {
        return service.findAll()
    }

    @PostMapping("api/v1/province")
    fun addProvinces(@RequestParam name: String?): String {
       return if (name.isNullOrEmpty()) {
             "نام وارد نشده است"
        }else{
            service.save(Province(name = name))
            "دیتا با موفق ثبت شد"
        }

    }
}