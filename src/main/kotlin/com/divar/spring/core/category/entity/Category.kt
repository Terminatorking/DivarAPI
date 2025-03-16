package com.divar.spring.core.category.entity

import jakarta.persistence.*

@Entity(name = "category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    val icon: String = "",

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    val parent: Category? = null,

    @OneToMany(mappedBy = "parent")
    val children: List<Category> = listOf()
)
