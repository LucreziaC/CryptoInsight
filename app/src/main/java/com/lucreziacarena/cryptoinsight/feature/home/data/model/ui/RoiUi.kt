package com.lucreziacarena.cryptoinsight.feature.home.data.model.ui

import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.Roi

data class RoiUi(
    val currency: String,
    val percentage: Double,
    val times: Double
)

fun Roi.toUiModel():RoiUi=RoiUi(
    currency,
    percentage,
    times
)
