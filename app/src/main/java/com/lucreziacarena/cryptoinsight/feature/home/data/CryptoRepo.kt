package com.lucreziacarena.cryptoinsight.feature.home.data

import com.lucreziacarena.cryptoinsight.feature.home.data.response.CryptoList
import retrofit2.Response
import javax.inject.Inject

class CryptoRepo @Inject constructor(
    private val cryptoApi:CryptoApi
): CryptoRepoInterface {
    override suspend fun getTopTenCryptoMarketCap(currency:String, order:String, perPage:Int,pageNum:Int, sparkline:Boolean): Response<CryptoList> {
        return cryptoApi.getTopTenCrypto(currency, order, perPage, pageNum, sparkline);
    }
}
