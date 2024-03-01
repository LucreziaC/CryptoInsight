package com.lucreziacarena.cryptoinsight.feature.detail.model.ui

import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.detail.Links

data class LinksUI(
    val homepage: List<String>,
)

fun Links.toUiModel(): LinksUI = LinksUI(
    homepage=homepage
)
