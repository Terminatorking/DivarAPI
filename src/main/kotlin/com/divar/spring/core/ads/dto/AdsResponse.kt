package com.divar.spring.core.ads.dto

import com.divar.spring.core.ads.entity.Ads
import com.divar.spring.core.category.dto.CategoryResponse
import com.divar.spring.core.category.dto.toCategoryResponse
import com.divar.spring.core.image.dto.ImageResponse
import com.divar.spring.core.image.dto.toResponse
import com.divar.spring.core.location.dto.NeighbourhoodResponse
import com.divar.spring.core.location.dto.toNeighbourhoodResponse
import com.divar.spring.core.parameter.dto.answer.ParameterAnswerResponse
import com.divar.spring.core.parameter.dto.answer.toResponse
import com.divar.spring.core.user.dto.UserResponse
import com.divar.spring.core.user.dto.toUserResponse
import java.time.Instant


data class AdsResponse(
    val id: Long,

    val title: String,

    val description: String,

    val price: String,

    val neighborhood: NeighbourhoodResponse,

    val user: UserResponse,

    val category: CategoryResponse,

    val images: List<ImageResponse>,

    val answers: List<ParameterAnswerResponse>,

    val createAt: Instant? = null,

    val updatedAt: Instant? = null,
)

fun Ads.toResponse(): AdsResponse {
    return AdsResponse(
        id = id,
        title = title,
        description = description,
        price = price,
        neighborhood = neighbourhood.toNeighbourhoodResponse(),
        user = user.toUserResponse(""),
        category = category.toCategoryResponse(false),
        images = images.map { it.toResponse() },
        answers = answers.map { it.toResponse() },
        createAt = createAt,
        updatedAt = updatedAt
    )
}