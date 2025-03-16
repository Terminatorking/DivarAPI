package com.divar.spring.core.user.controller

import com.divar.spring.core.user.dto.UserRequest
import com.divar.spring.core.user.dto.toUser
import com.divar.spring.core.user.dto.toUserResponse
import com.divar.spring.core.user.service.UserService
import com.divar.spring.utils.security.JwtService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/")
class UserController(val userService: UserService, val jwtService: JwtService) {
    @PostMapping("user")
    fun addUser(@RequestBody userRequest: UserRequest? = null): Any {
        return if (userRequest == null) {
            "لطفا اطلاعات کاربر را وارد کنید"
        } else {
            val user = userRequest.toUser()
            val token = jwtService.generate(user)
            val savedUser = userService.save(user)
            return savedUser.toUserResponse(token)
        }
    }
}