package com.lucreziacarena.cryptoinsight.feature.detail.model.ui

import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.detail.CryptoDetailModel


data class CryptoDetailUi(

    val description: DescriptionUI?,

    val links: LinksUI?,

)

fun CryptoDetailModel.toUiModel(): CryptoDetailUi = CryptoDetailUi(
    description= description?.toUiModel(),
    links=links?.toUiModel()
)
