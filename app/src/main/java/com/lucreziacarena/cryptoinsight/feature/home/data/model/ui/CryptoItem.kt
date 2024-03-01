package com.lucreziacarena.cryptoinsight.feature.home.data.model.ui

import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.CryptoList
import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.CryptoListItem
import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.Roi


class CryptoListUI : ArrayList<CryptoItemUI>()


data class CryptoItemUI(
    val ath: Double?,
    val ath_change_percentage: Double?,
    val ath_date: String?,
    val atl: Double?,
    val atl_change_percentage: Double?,
    val atl_date: String?,
    val circulating_supply: Double?,
    val current_price: Double?,
    val fully_diluted_valuation: Long?,
    val high_24h: Double?,
    val id: String?,
    val image: String?,
    val last_updated: String?,
    val low_24h: Double?,
    val market_cap: Long?,
    val market_cap_change_24h: Double?,
    val market_cap_change_percentage_24h: Double?,
    val market_cap_rank: Int?,
    val max_supply: Double?,
    val name: String?,
    val price_change_24h: Double?,
    val price_change_percentage_24h: Double?,
    val roi: RoiUi?,
    val symbol: String?,
    val total_supply: Double?,
    val total_volume: Long?
)

fun CryptoListItem.toUiModel(): CryptoItemUI = CryptoItemUI(
    ath = ath,
    ath_change_percentage,
    ath_date,
    atl,
    atl_change_percentage,
    atl_date,
    circulating_supply,
    current_price,
    fully_diluted_valuation,
    high_24h,
    id,
    image,
    last_updated,
    low_24h,
    market_cap,
    market_cap_change_24h,
    market_cap_change_percentage_24h,
    market_cap_rank,
    max_supply,
    name,
    price_change_24h,
    price_change_percentage_24h,
    roi?.toUiModel(),
    symbol,
    total_supply,
    total_volume
)

