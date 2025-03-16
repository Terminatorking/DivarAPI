package com.divar.spring.core.user.dto

import com.divar.spring.core.user.entity.User
import java.time.Instant

data class UserResponse(
    val name: String,
    val family: String,
    val email: String,
    val token: String,
    val mobile: String,
    val createdAt: Instant?,
    val updatedAt: Instant?,
)

fun User.toUserResponse(token: String): UserResponse {
    return UserResponse(
        name = this.name,
        family = this.family,
        email = this.email,
        token = token,
        mobile = this.mobile,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
}