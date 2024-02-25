package com.lucreziacarena.cryptoinsight.feature.home.contract

import com.lucreziacarena.cryptoinsight.feature.home.data.model.ui.CryptoItemUI
import com.waseem.libroom.core.mvi.MviAction
import com.waseem.libroom.core.mvi.MviEvent
import com.waseem.libroom.core.mvi.MviResult
import com.waseem.libroom.core.mvi.MviStateReducer
import com.waseem.libroom.core.mvi.MviViewState
import javax.inject.Inject

sealed class HomeAction : MviAction {
    object Load : HomeAction()
    data class BookItemClicked(val bookId: String) : HomeAction()
}

sealed class DomainResult : MviResult {
    object Loading: DomainResult()
    data class Content(val content: List<CryptoItemUI>) : DomainResult()
    object Failure : DomainResult()
}

sealed class HomeEvent : MviEvent {
    object NavigateToBooksList : HomeEvent()
    data class NavigateToBookDetail(val bookId: String): HomeEvent()
}

sealed class HomeState : MviViewState {
    object DefaultState : HomeState()
    object LoadingState: HomeState()
    data class HomeContentState(val content: List<CryptoItemUI>) : HomeState()
    object ErrorState : HomeState()
}

class HomeReducer @Inject constructor() : MviStateReducer<HomeState, DomainResult> {
    override fun HomeState.reduce(result: DomainResult): HomeState {
        return when(val previousState = this) {
            is HomeState.DefaultState -> previousState + result
            is HomeState.ErrorState -> previousState + result
            is HomeState.HomeContentState -> previousState + result
            is HomeState.LoadingState -> previousState + result
        }
    }

    private operator fun HomeState.DefaultState.plus(result: DomainResult): HomeState {
        return when(result) {
            DomainResult.Loading -> HomeState.LoadingState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun HomeState.LoadingState.plus(result: DomainResult): HomeState {
        return when(result) {
            DomainResult.Loading -> HomeState.LoadingState
            is DomainResult.Content -> HomeState.HomeContentState(content = result.content)
            DomainResult.Failure -> HomeState.ErrorState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun HomeState.ErrorState.plus(result: DomainResult): HomeState {
        return when(result) {
            DomainResult.Loading -> HomeState.LoadingState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun HomeState.HomeContentState.plus(result: DomainResult): HomeState {
        return when(result) {
            DomainResult.Loading -> HomeState.LoadingState
            is DomainResult.Content -> HomeState.HomeContentState(content = result.content)
            else -> throw IllegalStateException("unsupported")
        }
    }
}
