package com.divar.spring.core.user.controller

import com.divar.spring.core.user.dto.UserRequest
import com.divar.spring.core.user.dto.toUser
import com.divar.spring.core.user.dto.toUserResponse
import com.divar.spring.core.user.service.UserService
import com.divar.spring.utils.security.JwtService
import org.springframework.web.bind.annotation.*

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

    @GetMapping("user")
    fun getUser(@RequestHeader("Authorization") token: String?): Any? {
        if (token.isNullOrEmpty()) {
            return "لطفا مقادیر مورد نیاز وارد نمایید!"
        } else {
            val mobile = jwtService.extractMobile(token)
            if (mobile.isNullOrEmpty()) {
                return "توکن معتبر نمی باشد!"
            } else {
                val user = userService.findByMobile(mobile)
                return user?.toUserResponse("")
            }
        }
    }
}