package com.lucreziacarena.cryptoinsight.feature.detail.viewmodel

import com.lucreziacarena.cryptoinsight.feature.detail.contract.*
import com.lucreziacarena.cryptoinsight.feature.detail.model.ui.history.CryptoHistoryUI
import com.lucreziacarena.cryptoinsight.feature.detail.model.ui.history.toUiModel
import com.lucreziacarena.cryptoinsight.feature.detail.model.ui.toUiModel
import com.lucreziacarena.cryptoinsight.network.CryptoRepoInterface
import com.lucreziacarena.cryptoinsight.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CryptoRepoInterface,
    private val defaultDispatcher: CoroutineDispatcher,
) :
    BaseViewModel<DetailAction, DetailResult, DetailEvent, DetailState, DetailReducer>(
        initialState = DetailState.DefaultState,
        reducer = DetailReducer()
    ) {
    init {
        //action(DetailAction.LoadData)
    }

    override fun DetailAction.process(): Flow<DetailResult> {
        return when (this) {
            is DetailAction.LoadData -> {
                getCryptoDetails(this.cryptoId)
            }//loadCryptoList()
            //HomeAction.ViewAllClicked -> TODO()
            is DetailAction.OnCryptoClick -> emitEvent(
                event = DetailEvent.NavigateToDetail(cryptoId = cryptoId)
            )

            DetailAction.ViewAllClicked -> TODO()
        }
    }

    private fun getCryptoDetails(cryptoId: String): Flow<DetailResult> {
        return flow<DetailResult> {
            repository.getCryptoDetail(cryptoId)
                .flowOn(defaultDispatcher)
                .collect { result ->
                    when (result) {
                        is ApiResult.Loading -> {
                            emit(DetailResult.Loading)
                        }

                        is ApiResult.Error -> {
                            emit(DetailResult.Failure)
                        }

                        else -> {
                            if (result.data != null) {

                                emit(DetailResult.Success(result.data.toUiModel()))
                            }//is content state
                            else {
                                emit(DetailResult.Failure)
                            }

                        }

                    }
                }
        }
    }
    }

