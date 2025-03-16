package com.divar.spring.core.category.service

import com.divar.spring.core.category.entity.Category
import com.divar.spring.core.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(val categoryRepository: CategoryRepository) {
    fun findAll(): List<Category> = categoryRepository.findAll().filter { it.parent == null }
    fun saveAll(categories: List<Category>): List<Category> = categoryRepository.saveAll(categories)
    fun save(category: Category): Category = categoryRepository.save(category)
    fun count(): Long = categoryRepository.count()
}