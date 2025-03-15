package com.divar.spring.core.location.entity

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity(name = "city")
data class City(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @NotNull
    var name: String,
    @NotNull
    @ManyToOne
    @JoinColumn(name = "province_id")
    var province: Province? = null,

    @OneToMany(mappedBy = "city", targetEntity = NeighbourHood::class)
    var neighborhoods: MutableList<NeighbourHood>? = mutableListOf()
)
