package com.divar.spring.core.image.entity

import jakarta.persistence.*

@Entity(name = "image")
data class Image(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val path: String,
)
