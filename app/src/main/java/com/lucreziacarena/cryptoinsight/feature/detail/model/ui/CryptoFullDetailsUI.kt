package com.lucreziacarena.cryptoinsight.feature.detail.model.ui

import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.CryptoFullDetails
import com.lucreziacarena.cryptoinsight.feature.detail.model.ui.history.CryptoHistoryUI
import com.lucreziacarena.cryptoinsight.feature.detail.model.ui.history.toUiModel

data class CryptoFullDetailsUI(val detail: CryptoDetailUi?, val history: CryptoHistoryUI?)

fun CryptoFullDetails.toUiModel():CryptoFullDetailsUI=CryptoFullDetailsUI(
    detail?.toUiModel(),
    history?.toUiModel()
)
