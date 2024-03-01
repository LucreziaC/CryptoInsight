package com.lucreziacarena.cryptoinsight.feature.detail.composable

import android.text.Html
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.lucreziacarena.cryptoinsight.feature.detail.contract.DetailAction
import com.lucreziacarena.cryptoinsight.feature.detail.contract.DetailState
import com.lucreziacarena.cryptoinsight.feature.detail.viewmodel.DetailViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    cryptoId: String,
    navController: NavController
) {
    viewModel.action(DetailAction.LoadData(cryptoId = cryptoId))

    val descriptionItem = AccordionModel(
        header = "Description",
        content = { Column {} }
    )

    val linksItem = AccordionModel(
        header = "Links",
        content = { Column {} }
    )

    val HistoryItem = AccordionModel(
        header = "History Prices",
        content = { Column {} }

    )



    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(
                    paddingValues
                )
        ) {

            when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
                is DetailState.Content -> {
                    val details = state.content.detail
                    val history = state.content.history
                    var description: String = ""
                    if (!details?.description?.it.isNullOrBlank())
                        description = details?.description?.it!!
                    else if (!details?.description?.en.isNullOrBlank())
                        description = details?.description?.en!!
                    val text =
                        Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)

                    descriptionItem.content = {
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ){
                            HtmlTextView(
                                htmlText = description,
                                onClickLink = { url ->
                                    // Handle link click
                                }
                            )
                        }

                    }
                    HistoryItem.content = {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                                .padding(
                                    horizontal = 8.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            if (history != null && history.prices.isNotEmpty()) {
                                items(history.prices.size) { index ->
                                    val item = history.prices[index]
                                    Card(
                                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                                        shape = RoundedCornerShape(20.dp),

                                        ) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(10.dp)

                                        ) {
                                            val instant = Instant.ofEpochMilli(item.first().toLong())
                                            val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                                            val time = instant.atZone(ZoneId.systemDefault()).toLocalTime()
                                            val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
                                            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault())

                                            val formattedDate = dateFormatter.format(date)
                                            val formattedTime = timeFormatter.format(time)

                                            Column {
                                                Text(text = formattedDate ?: "")
                                                Text(text = formattedTime ?: "")
                                            }
                                            Text(text = item.last().toString() ?: "")

                                        }
                                    }
                                }
                            }
                        }
                    }

                    val group = listOf(descriptionItem, linksItem, HistoryItem)
                    AccordionGroup(modifier = Modifier.padding(top = 8.dp), group = group)
                }

                DetailState.DefaultState -> Text(text = "default")
                DetailState.ErrorState -> Text(text = "error")
                DetailState.LoadingState -> Text(text = "loading")
            }
        }
    }
}


