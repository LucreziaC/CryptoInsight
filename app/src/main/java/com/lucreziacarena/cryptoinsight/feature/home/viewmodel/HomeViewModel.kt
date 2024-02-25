package com.lucreziacarena.cryptoinsight.feature.home.viewmodel

import com.lucreziacarena.cryptoinsight.feature.home.contract.*
import com.lucreziacarena.cryptoinsight.feature.home.data.CryptoRepoInterface
import com.lucreziacarena.cryptoinsight.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    reducer: HomeReducer,
) :
    BaseViewModel<HomeAction, DomainResult, HomeEvent, HomeState, HomeReducer>(
        initialState = HomeState.DefaultState,
        reducer = reducer
    ) {
    override fun HomeAction.process(): Flow<DomainResult> {
        TODO("Not yet implemented")
    }
}

