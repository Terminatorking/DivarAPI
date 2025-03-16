package com.divar.spring.core.user.dto

import com.divar.spring.core.user.entity.User

data class UserRequest(
    val name: String,
    val family: String,
    val email: String,
    val password: String,
    val mobile: String,
)

fun UserRequest.toUser(): User {
    return User(
        name = this.name,
        family = this.family,
        email = this.email,
        password = this.password,
        mobile = this.mobile
    )
}