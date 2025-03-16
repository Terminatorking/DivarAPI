package com.divar.spring.core.location.entity

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity(name = "neighbourhood")
data class NeighbourHood(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @NotNull
    var name: String,

    @NotNull
    @ManyToOne
    @JoinColumn(name = "city_id")
    var city: City?=null
)
