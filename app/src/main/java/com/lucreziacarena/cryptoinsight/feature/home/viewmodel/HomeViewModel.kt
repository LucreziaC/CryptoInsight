package com.lucreziacarena.cryptoinsight.feature.home.viewmodel

import ApiResult
import com.lucreziacarena.cryptoinsight.feature.home.contract.*
import com.lucreziacarena.cryptoinsight.feature.home.data.model.ui.toUiModel
import com.lucreziacarena.cryptoinsight.network.CryptoRepoInterface
import com.lucreziacarena.cryptoinsight.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    //reducer: HomeReducer,
    private val repository: CryptoRepoInterface,
    private val defaultDispatcher: CoroutineDispatcher,
    ) :
    BaseViewModel<HomeAction, HomeResult, HomeEvent, HomeState, HomeReducer>(
        initialState = HomeState.DefaultState,
        reducer = HomeReducer()
    ) {

    init {
        action(HomeAction.Load)
    }

    override fun HomeAction.process(): Flow<HomeResult> {
        return when (this) {
            HomeAction.Load -> loadCryptoList()
            //HomeAction.ViewAllClicked -> TODO()
             is HomeAction.OnCryptoClick -> emitEvent(
                 event = HomeEvent.NavigateToDetail(cryptoId = cryptoId)
             )
            HomeAction.ViewAllClicked -> TODO()
        }
    }

    private fun loadCryptoList(): Flow<HomeResult> {
        return flow<HomeResult> {
            repository.getTopTenCryptoMarketCap(null, null, null, null, null)
                .flowOn(defaultDispatcher)
                .collect { result ->
                    when (result) {
                        is ApiResult.Loading -> {emit(HomeResult.Loading)}
                        is ApiResult.Error -> {emit(HomeResult.Failure)}
                        else -> {
                            if (result.data!=null) emit(HomeResult.Success(result.data.map{it.toUiModel()}))//is content state
                            else{
                                emit(HomeResult.Failure)
                            }

                        }

                    }
                }
        }

    }
}

