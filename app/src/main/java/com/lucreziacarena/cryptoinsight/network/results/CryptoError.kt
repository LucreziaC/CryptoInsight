package com.lucreziacarena.cryptoinsight.network.results

sealed class CryptoError {
    data object NoCryptoFound : CryptoError()
    data class GenericError(val message: String?): CryptoError()
}
