package com.lucreziacarena.cryptoinsight.feature.detail.model.ui

import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.detail.Links

data class LinksUI(
    val blockchain_site: List<String>,
)

fun Links.toUiModel(): LinksUI = LinksUI(
    blockchain_site=blockchain_site
)
