package com.divar.spring.core.ads.entity

import com.divar.spring.core.image.entity.Image
import com.divar.spring.core.location.entity.NeighbourHood
import com.divar.spring.core.user.entity.User
import jakarta.persistence.*

@Entity(name = "ads")
data class Ads(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    val title: String,
    val description: String,
    val price: String,
    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    val neighbourHood: NeighbourHood,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @OneToMany(fetch = FetchType.LAZY)
    val images: List<Image>,
)
