package com.divar.spring.core.image.service

import com.divar.spring.core.image.entity.Image
import com.divar.spring.core.image.repository.ImageRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.io.path.pathString

@Service
class ImageService(val imageRepository: ImageRepository) {
    private final val UPLOAD_DIR = "uploads/"

    init {
        val dir = File(UPLOAD_DIR)
        if (dir.exists().not()) {
            dir.mkdirs()
        }
    }

    fun save(image: MultipartFile): Image {
        val fileName = "${System.currentTimeMillis()}_${image.originalFilename}"
        val filePath = Paths.get("$UPLOAD_DIR$fileName")
        Files.copy(image.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)
        return imageRepository.save(Image(path = filePath.pathString))
    }
}