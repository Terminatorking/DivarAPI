package com.divar.spring.core.user.controller

import com.divar.spring.core.user.dto.UserRequest
import com.divar.spring.core.user.dto.toUser
import com.divar.spring.core.user.dto.toUserResponse
import com.divar.spring.core.user.service.UserService
import com.divar.spring.utils.response.ApiResponse
import com.divar.spring.utils.response.BadRequestError
import com.divar.spring.utils.response.NotFoundError
import com.divar.spring.utils.response.UnauthorizedError
import com.divar.spring.utils.security.JwtService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")
class UserController(val userService: UserService, val jwtService: JwtService) {

    @PostMapping("user")
    fun registerUser(@RequestBody userRequest: UserRequest? = null): Any {
        if (userRequest == null) {
            return ApiResponse.error(BadRequestError())
        } else if (userService.findByMobile(userRequest.mobile) != null) {
            return ApiResponse.error(BadRequestError(message = "کاربری با این مشخصات وجود دارد"))
        } else {
            val user = userRequest.toUser()
            val token = jwtService.generate(user)
            val savedUser = userService.save(user)
            val userResponse = savedUser.toUserResponse(token)
            return ApiResponse.success(userResponse)
        }
    }

    @PutMapping("user")
    fun updateUser(@RequestBody userRequest: UserRequest? = null): Any {
        if (userRequest == null) return ApiResponse.error(BadRequestError())
        return userService.findByMobile(userRequest.mobile)?.let { dbUser ->
            val user = userRequest.toUser()
            userService.save(user.copy(id = dbUser.id))
        } ?: run {
            ApiResponse.error(NotFoundError(message = "کاربری با این مشخصات یافت نشد"))
        }
    }

    @GetMapping("user")
    fun getUser(@RequestHeader("Authorization") token: String?): Any? {
        return if (token.isNullOrEmpty()) ApiResponse.error(BadRequestError(message = "لطفا توکن را وارد نمایید!"))
         else {
            val mobile = jwtService.extractMobile(token)
            if (mobile.isNullOrEmpty()) return ApiResponse.error(UnauthorizedError(message = "توکن معتبر نمی باشد!"))
            else {
                userService.findByMobile(mobile)?.let { dbUser ->
                   return ApiResponse.success(data = dbUser.toUserResponse(""))
                } ?: run {
                    ApiResponse.error(NotFoundError(message = "کاربری با این مشخصات یافت نشد"))
                }
            }
        }
    }

}