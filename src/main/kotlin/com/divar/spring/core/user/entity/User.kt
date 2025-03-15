package com.divar.spring.core.user.entity

import jakarta.persistence.*

@Entity(name = "user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    val name: String,
    val family: String
)
