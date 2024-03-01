package com.lucreziacarena.cryptoinsight.network

import ApiResult
import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.CryptoFullDetails
import com.lucreziacarena.cryptoinsight.feature.home.data.model.domain.CryptoList
import com.lucreziacarena.cryptoinsight.network.results.CryptoError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.inject.Inject


class CryptoRepo @Inject constructor(
    private val cryptoApi: CryptoApi
) : CryptoRepoInterface {
    override fun getTopTenCryptoMarketCap(
        currency: String?,
        order: String?,
        perPage: Int?,
        pageNum: Int?,
        sparkline: Boolean?
    ): Flow<ApiResult<CryptoList>> {
        return flow {
            try {
                emit(ApiResult.Loading())
                val result = cryptoApi.getTopTenCrypto(
                    currency ?: "eur",
                    order ?: "market_cap_desc",
                    perPage ?: 10,
                    pageNum ?: 1,
                    sparkline ?: false,
                    apiKey = "CG-NFBer6ufwMAkALEutD66hDi8"
                )
                if (result.isEmpty()) {
                    emit(ApiResult.Error(CryptoError.NoCryptoFound))
                } else {
                    emit(ApiResult.Success(data = result))
                }
            } catch (e: Exception) {
                emit(ApiResult.Error(CryptoError.GenericError(e.message)))
            }
        }

    }

    override fun getCryptoDetail(coinId: String): Flow<ApiResult<CryptoFullDetails>> {
        return flow {
            try {
                emit(ApiResult.Loading())
                val result=CryptoFullDetails(null, null)
                val detail = cryptoApi.getCryptoDetail(coinId, apiKey = "CG-NFBer6ufwMAkALEutD66hDi8")
                result.detail=detail
                try{
                    val today = LocalDate.now()
                    val zoneId = ZoneId.systemDefault() // or: ZoneId.of("Europe/Oslo");
                    val todayEpoch: String = today.atStartOfDay(zoneId).toEpochSecond().toString()
                    val aWeekAgoEpoch = today.minusDays(7).atStartOfDay(zoneId).toEpochSecond().toString()
                    val history = cryptoApi.getCryptoHistory(coinId,"eur",aWeekAgoEpoch, todayEpoch, "2", apiKey = "CG-NFBer6ufwMAkALEutD66hDi8" )
                    result.history=history
                }catch(e:Exception){}
                emit(ApiResult.Success(data = result))
            } catch (e: Exception) {
                emit(ApiResult.Error(CryptoError.GenericError(e.message)))
            }
        }
    }


}
