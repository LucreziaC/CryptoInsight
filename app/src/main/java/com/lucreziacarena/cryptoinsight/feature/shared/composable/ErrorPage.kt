package com.lucreziacarena.cryptoinsight.feature.shared.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lucreziacarena.cryptoinsight.network.results.CryptoError

@Composable
fun ErrorPage(
    cryptoError: CryptoError
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(it).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.Error, "Error icon")
            if (cryptoError is CryptoError.GenericError)
                Text(cryptoError.message ?: "GenericError")
            if (cryptoError is CryptoError.NoCryptoFound)
                Text("No Crypto Found")

        }
    }
}
