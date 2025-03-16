package com.divar.spring.core.location.controller

import com.divar.spring.core.location.dto.ProvinceResponse
import com.divar.spring.core.location.dto.toProvinceResponse
import com.divar.spring.core.location.entity.Province
import com.divar.spring.core.location.service.ProvinceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/")
class ProvinceController(val service: ProvinceService) {

    @GetMapping("province")
    fun getProvinces(): List<ProvinceResponse> {
        return service.findAll().map {
            it.toProvinceResponse(false)
        }
    }

    @PostMapping("province")
    fun addProvinces(@RequestParam name: String?): String {
       return if (name.isNullOrEmpty()) {
             "نام وارد نشده است"
        }else{
            service.save(Province(name = name))
            "دیتا با موفق ثبت شد"
        }
    }
}