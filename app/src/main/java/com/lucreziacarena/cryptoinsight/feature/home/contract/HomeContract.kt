package com.lucreziacarena.cryptoinsight.feature.home.contract

import com.lucreziacarena.cryptoinsight.feature.home.data.model.ui.CryptoItemUI
import com.lucreziacarena.cryptoinsight.utils.*
import javax.inject.Inject

sealed class HomeAction : MviAction {
    object Load : HomeAction()
    object ViewAllClicked : HomeAction()
    data class OnCryptoClick(val cryptoId: String) : HomeAction()
}

sealed class HomeResult : MviResult {
    object Loading: HomeResult()
    data class Success(val content: List<CryptoItemUI>) : HomeResult()
    object Failure : HomeResult()
}

sealed class HomeEvent : MviEvent, HomeResult() {
    data class NavigateToDetail(val cryptoId: String) : HomeEvent()
}

sealed class HomeState : MviViewState {
    data object DefaultState : HomeState()
    data object LoadingState: HomeState()
    data class Content(val content: List<CryptoItemUI>) : HomeState()
    data object ErrorState : HomeState()
}

class HomeReducer @Inject constructor() : MviStateReducer<HomeState, HomeResult> {
    override fun HomeState.reduce(result: HomeResult): HomeState {
        return when(val previousState = this) {
            is HomeState.DefaultState -> previousState + result
            is HomeState.ErrorState -> previousState + result
            is HomeState.Content -> previousState + result
            is HomeState.LoadingState -> previousState + result
        }
    }

    private operator fun HomeState.DefaultState.plus(result: HomeResult): HomeState {
        return when(result) {
            HomeResult.Loading -> HomeState.LoadingState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun HomeState.LoadingState.plus(result: HomeResult): HomeState {
        return when(result) {
            HomeResult.Loading -> HomeState.LoadingState
            is HomeResult.Success -> HomeState.Content(content = result.content)
            HomeResult.Failure -> HomeState.ErrorState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun HomeState.ErrorState.plus(result: HomeResult): HomeState {
        return when(result) {
            HomeResult.Loading -> HomeState.LoadingState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun HomeState.Content.plus(result: HomeResult): HomeState {
        return when(result) {
            HomeResult.Loading -> HomeState.LoadingState
            is HomeResult.Success -> HomeState.Content(content = result.content)
            else -> throw IllegalStateException("unsupported")
        }
    }
}
