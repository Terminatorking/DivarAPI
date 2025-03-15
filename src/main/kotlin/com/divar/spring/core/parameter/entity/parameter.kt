package com.divar.spring.core.parameter.entity

import com.divar.spring.core.category.entity.Category
import jakarta.persistence.*

@Entity(name = "parameter")
data class Parameter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val dataType: String,
    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category
)
