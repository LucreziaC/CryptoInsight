package com.lucreziacarena.cryptoinsight.network

import ApiResult
import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.CryptoList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CryptoRepoInterface {
     suspend fun getTopTenCryptoMarketCap(currency:String?, order:String?, perPage:Int?,pageNum:Int?, sparkline:Boolean?): Flow<ApiResult<CryptoList>>
}
