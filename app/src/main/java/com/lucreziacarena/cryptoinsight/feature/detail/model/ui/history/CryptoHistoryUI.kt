package com.lucreziacarena.cryptoinsight.feature.detail.model.ui.history

import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.history.CryptoHistory

data class CryptoHistoryUI(
    val market_caps: List<List<Double>>,
    val prices: List<List<Double>>,
    val total_volumes: List<List<Double>>
)
fun CryptoHistory.toUiModel(): CryptoHistoryUI = CryptoHistoryUI(
    market_caps,
    prices,
    total_volumes
)
