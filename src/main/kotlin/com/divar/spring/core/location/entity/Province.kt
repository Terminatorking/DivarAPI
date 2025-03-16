package com.divar.spring.core.location.entity

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity(name = "province")
data class Province(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @NotNull
    var name: String,

    @NotNull
    @OneToMany(mappedBy = "province", targetEntity = City::class)
    var cities: List<City>? =listOf()
)
