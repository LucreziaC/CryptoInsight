package com.lucreziacarena.cryptoinsight.feature.detail.model.domain.history

data class CryptoHistory(
    val market_caps: List<List<Double>>,
    val prices: List<List<Double>>,
    val total_volumes: List<List<Double>>
)
