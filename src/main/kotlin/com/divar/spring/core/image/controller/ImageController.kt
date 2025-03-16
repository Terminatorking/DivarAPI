package com.divar.spring.core.image.controller

import com.divar.spring.core.image.service.ImageService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/v1")
class ImageController(val imageService: ImageService) {
    @PostMapping("image/upload")
    fun uploadImage(@RequestParam("file") file: MultipartFile? = null): String {
        return if (file == null) {
            "file not found"
        } else {
            imageService.save(file).path
        }
    }
}