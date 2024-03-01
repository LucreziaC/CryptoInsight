package com.lucreziacarena.cryptoinsight.network

import ApiResult
import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.CryptoFullDetails
import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.detail.CryptoDetailModel
import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.history.CryptoHistory
import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.CryptoList
import kotlinx.coroutines.flow.Flow

interface CryptoRepoInterface {
      fun getTopTenCryptoMarketCap(currency:String?, order:String?, perPage:Int?,pageNum:Int?, sparkline:Boolean?): Flow<ApiResult<CryptoList>>
      fun getCryptoDetail(coinId:String): Flow<ApiResult<CryptoFullDetails>>
}
