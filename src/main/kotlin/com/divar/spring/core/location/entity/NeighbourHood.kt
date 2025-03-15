package com.divar.spring.core.location.entity

import jakarta.persistence.*

@Entity(name = "neighbourhood")
data class NeighbourHood(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    @ManyToOne
    @JoinColumn(name = "city_id")
    val city: City
)
