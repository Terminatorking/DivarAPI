package com.divar.spring.core.category.dto

import com.divar.spring.core.category.entity.Category

data class CategoryResponse(
    val id: Long?,
    val name: String,
    val icon: String,
    val children: List<CategoryResponse>,
)

fun Category.toCategoryResponse(includeChildren: Boolean = true): CategoryResponse {
    return CategoryResponse(
        id = this.id,
        name = this.name,
        icon = this.icon,
        children = if (includeChildren)
            this.children.map {
                category: Category -> category.toCategoryResponse()
            } else listOf()
    )
}