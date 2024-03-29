package com.lucreziacarena.cryptoinsight.feature.detail.contract

import com.lucreziacarena.cryptoinsight.feature.detail.model.ui.CryptoFullDetailsUI
import com.lucreziacarena.cryptoinsight.feature.home.contract.HomeResult
import com.lucreziacarena.cryptoinsight.feature.home.contract.HomeState
import com.lucreziacarena.cryptoinsight.network.results.CryptoError
import com.lucreziacarena.cryptoinsight.utils.*
import javax.inject.Inject

sealed class DetailAction : MviAction {
    data class LoadData(val cryptoId: String) : DetailAction()
}

sealed class DetailResult : MviResult {
    data object Loading: DetailResult()
    data class Success(val content: CryptoFullDetailsUI) : DetailResult()
    data class Failure(val error: CryptoError) : DetailResult()
}

sealed class DetailEvent
    : MviEvent, DetailResult() {
    data class NavigateToDetail(val cryptoId: String) : DetailEvent()
}

sealed class DetailState : MviViewState {
    data object DefaultState : DetailState()
    data object LoadingState: DetailState()
    data class Content(val content: CryptoFullDetailsUI) : DetailState()
    data class ErrorState(val error: CryptoError) : DetailState()
}

class DetailReducer @Inject constructor() : MviStateReducer<DetailState, DetailResult> {
    override fun DetailState.reduce(result: DetailResult): DetailState {
        return when(val previousState = this) {
            is DetailState.DefaultState -> previousState + result
            is DetailState.ErrorState -> previousState + result
            is DetailState.Content -> previousState + result
            is DetailState.LoadingState -> previousState + result
        }
    }

    private operator fun DetailState.DefaultState.plus(result: DetailResult): DetailState {
        return when(result) {
            DetailResult.Loading -> DetailState.LoadingState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun DetailState.LoadingState.plus(result: DetailResult): DetailState {
        return when(result) {
            DetailResult.Loading -> DetailState.LoadingState
            is DetailResult.Success -> DetailState.Content(content = result.content)
            is DetailResult.Failure -> DetailState.ErrorState(result.error)
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun DetailState.ErrorState.plus(result: DetailResult): DetailState {
        return when(result) {
            DetailResult.Loading -> DetailState.LoadingState
            else -> throw IllegalStateException("unsupported")
        }
    }

    private operator fun DetailState.Content.plus(result: DetailResult): DetailState {
        return when(result) {
            DetailResult.Loading -> DetailState.LoadingState
            is DetailResult.Success -> DetailState.Content(content = result.content)
            else -> throw IllegalStateException("unsupported")
        }
    }
}
