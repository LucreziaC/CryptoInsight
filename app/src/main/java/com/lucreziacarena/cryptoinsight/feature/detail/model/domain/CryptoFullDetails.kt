package com.lucreziacarena.cryptoinsight.feature.detail.model.domain

import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.detail.CryptoDetailModel
import com.lucreziacarena.cryptoinsight.feature.detail.model.domain.history.CryptoHistory
import com.lucreziacarena.cryptoinsight.feature.detail.model.ui.CryptoDetailUi
import com.lucreziacarena.cryptoinsight.feature.detail.model.ui.history.CryptoHistoryUI

data class CryptoFullDetails(var detail: CryptoDetailModel?, var history: CryptoHistory?)
