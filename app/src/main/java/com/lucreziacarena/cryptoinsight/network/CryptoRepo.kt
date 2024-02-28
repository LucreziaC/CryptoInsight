package com.lucreziacarena.cryptoinsight.network

import ApiResult
import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.CryptoList
import com.lucreziacarena.cryptoinsight.network.results.CryptoError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class CryptoRepo @Inject constructor(
    private val cryptoApi: CryptoApi
): CryptoRepoInterface {
    override suspend fun getTopTenCryptoMarketCap(currency:String?, order:String?, perPage:Int?,pageNum:Int?, sparkline:Boolean?): Flow<ApiResult<CryptoList>> {
        return flow{
            try {
                emit(ApiResult.Loading())
                val result = cryptoApi.getTopTenCrypto(currency ?: "eur", order ?:"market_cap_desc", perPage?:10, pageNum?:1, sparkline?:false);
                if(result.isEmpty()){
                    emit(ApiResult.Error(CryptoError.NoCryptoFound))
                }else{
                    emit(ApiResult.Success(data=result))
                }
            }catch (e:Exception){
                emit(ApiResult.Error(CryptoError.GenericError(e.message)))
            }
        }

    }
}
