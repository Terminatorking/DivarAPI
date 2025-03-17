package com.divar.spring.core.user.controller

import com.divar.spring.core.user.dto.UserRequest
import com.divar.spring.core.user.dto.toUser
import com.divar.spring.core.user.dto.toUserResponse
import com.divar.spring.core.user.service.UserService
import com.divar.spring.utils.response.*
import com.divar.spring.utils.security.JwtService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/")
class UserController(
    val userService: UserService,
    val jwtService: JwtService
) {

    @PostMapping("user/register")
    fun registerUser(
        @RequestBody userRequest: UserRequest? = null
    ): Any {
        return if (userRequest == null) ApiResponse.error(BadRequestError())
        else if (userService.findByMobile(userRequest.mobile) != null) ApiResponse.error(BadRequestError(message = "کاربری با این شماره وجود دارد"))
        else if (userRequest.password != userRequest.repeatPassword) ApiResponse.error(BadRequestError(message = "رمز عبورها یکسان نیستند!"))
        else {
            val user = userRequest.toUser()
            val token = jwtService.generate(user)
            val savedUser = userService.save(user)
            val userResponse = savedUser.toUserResponse(token)
            return ApiResponse.success(userResponse)
        }
    }

    @PostMapping("user/login")
    fun loginUser(
        @RequestBody userRequest: UserRequest? = null
    ): Any {
        if (userRequest == null) return ApiResponse.error(BadRequestError())
        userService.findByMobile(userRequest.mobile)?.let { user ->
            val token = jwtService.generate(user)
            return ApiResponse.success(user.toUserResponse(token))
        } ?: run {
            return ApiResponse.error(InvalidCredentialsError())
        }
    }

    @PutMapping("user")
    fun updateUser(
        @RequestBody userRequest: UserRequest? = null
    ): Any {
        if (userRequest == null) return ApiResponse.error(BadRequestError())
        return userService.findByMobile(userRequest.mobile)?.let { dbUser ->
            val user = userRequest.toUser()
            userService.save(user.copy(id = dbUser.id))
        } ?: run {
            ApiResponse.error(NotFoundError(message = "کابری با این مشخصات یافت نشد!"))
        }

    }

    @GetMapping("user")
    fun getUser(
        @RequestHeader("Authorization") token: String?,
    ): Any? {
        return if (token.isNullOrEmpty()) ApiResponse.error(
            BadRequestError(
                message = "لطفا توکن را وارد نمایید!"
            )
        )
        else {
            val mobile = jwtService.extractMobile(token)
            if (mobile.isNullOrEmpty()) return ApiResponse.error(
                UnauthorizedError(
                    message = "توکن معتبر نمی باشد!"
                )
            )
            else {
                userService.findByMobile(mobile)?.let { dbUser ->
                    return ApiResponse.success(
                        data = dbUser.toUserResponse(token = "")
                    )
                } ?: run {
                    ApiResponse.error(
                        NotFoundError(
                            message = "کابری با این مشخصات یافت نشد!"
                        )
                    )
                }
            }
        }
    }
}