package com.divar.spring.core.user.repository

import com.divar.spring.core.user.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByMobile(mobile: String): User?
}