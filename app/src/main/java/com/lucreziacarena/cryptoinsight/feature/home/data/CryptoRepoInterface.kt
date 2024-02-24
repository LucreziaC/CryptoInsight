package com.lucreziacarena.cryptoinsight.feature.home.data

import com.lucreziacarena.cryptoinsight.feature.home.data.response.CryptoList
import retrofit2.Response

interface CryptoRepoInterface {
     suspend fun getTopTenCryptoMarketCap(currency:String, order:String, perPage:Int,pageNum:Int, sparkline:Boolean): Response<CryptoList>
}
