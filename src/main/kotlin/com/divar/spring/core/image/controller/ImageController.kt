package com.divar.spring.core.image.controller

import com.divar.spring.core.image.service.ImageService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/")
class ImageController(val service: ImageService) {

}