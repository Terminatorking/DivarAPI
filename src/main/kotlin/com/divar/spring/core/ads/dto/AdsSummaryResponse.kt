package com.divar.spring.core.ads.dto

import com.divar.spring.core.ads.entity.Ads
import com.divar.spring.core.image.dto.ImageResponse
import com.divar.spring.core.image.dto.toResponse
import com.divar.spring.core.location.dto.NeighbourhoodResponse
import com.divar.spring.core.location.dto.toNeighbourhoodResponse
import java.time.Instant

data class AdsSummaryResponse(
    val id: Long,

    val title: String,

    val price: String,

    val neighborhood: NeighbourhoodResponse,

    val previewImage: ImageResponse?,

    val createAt: Instant? = null,
)

fun Ads.toSummaryResponse(): AdsSummaryResponse {
    return AdsSummaryResponse(
        id = id,
        title = title,
        price = price,
        neighborhood = neighbourhood.toNeighbourhoodResponse(),
        previewImage = images.firstOrNull()?.toResponse(),
        createAt = createAt,
    )
}