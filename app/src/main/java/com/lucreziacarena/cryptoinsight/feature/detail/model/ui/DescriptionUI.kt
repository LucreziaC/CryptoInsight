package com.lucreziacarena.cryptoinsight.feature.detail.model.ui

import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.detail.Description

data class DescriptionUI(
    val en: String?,
    val it: String?,
)

fun Description.toUiModel(): DescriptionUI = DescriptionUI(
    en=en,
    it=it
)
