package com.divar.spring.core.user.service

import com.divar.spring.core.user.entity.User
import com.divar.spring.core.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun hashPassword(password: String): String {
        val bCryptPasswordEncoder = BCryptPasswordEncoder()
        return bCryptPasswordEncoder.encode(password)
    }

    fun save(user: User): User {
        return userRepository.save(user.copy(password = hashPassword(user.password)))
    }
}