package com.divar.spring.core.category.dto

import com.divar.spring.core.category.entity.Category

data class CategoryResponse(
    val id: Long?,
    val name: String,
    val icon: String,
    val children: List<CategoryResponse>? = null,
)

fun Category.toCategoryResponse(): CategoryResponse {
    return CategoryResponse(
        id = this.id,
        name = this.name,
        icon = this.icon,
        children = this.children.map { category -> category.toCategoryResponse() })
}