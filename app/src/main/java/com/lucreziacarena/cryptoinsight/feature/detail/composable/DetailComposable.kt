package com.lucreziacarena.cryptoinsight.feature.detail.composable

import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.lucreziacarena.cryptoinsight.feature.detail.contract.DetailAction
import com.lucreziacarena.cryptoinsight.feature.detail.contract.DetailState
import com.lucreziacarena.cryptoinsight.feature.detail.viewmodel.DetailViewModel
import com.lucreziacarena.cryptoinsight.feature.home.contract.HomeState
import com.lucreziacarena.cryptoinsight.feature.shared.composable.ErrorPage
import com.lucreziacarena.cryptoinsight.feature.shared.composable.LoadingPage
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    cryptoId: String,
    name: String,
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

    val historyItem = AccordionModel(
        header = "Last week prices",
        content = { Column {} }

    )



    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(name.uppercase(Locale.getDefault())) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            "go back arrow"
                        )
                    }
                }
            )
        },

        ) { paddingValues ->
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
                    val links = details?.links?.homepage
                    val uriHandler = LocalUriHandler.current

                    //set description languages
                    var description: String = ""
                    if (!details?.description?.it.isNullOrBlank())
                        description = details?.description?.it!!
                    else if (!details?.description?.en.isNullOrBlank())
                        description = details?.description?.en!!

                    //set content for each accordion item
                    descriptionItem.content = {
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HtmlTextView(
                                htmlText = description,
                                onClickLink = { url ->
                                    uriHandler.openUri(url)

                                }
                            )
                        }

                    }
                    historyItem.content = {
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

                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(10.dp).fillMaxWidth()

                                    ) {
                                        val instant = Instant.ofEpochMilli(item.first().toLong())
                                        val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
                                        val time = instant.atZone(ZoneId.systemDefault()).toLocalTime()
                                        val dateFormatter =
                                            DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
                                        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault())

                                        val formattedDate = dateFormatter.format(date)
                                        val formattedTime = timeFormatter.format(time)

                                        Column {
                                            Text(text = formattedDate ?: "")
                                            Text(
                                                text = formattedTime ?: "",
                                                style = TextStyle(fontSize = 12.sp, color = Color.LightGray)
                                            )
                                        }
                                        Text(text = (item.last().toString() + " €") ?: "")

                                    }
                                    HorizontalDivider()


                                }
                            }
                        }
                    }
                    linksItem.content = {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                                .padding(
                                    horizontal = 8.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            if (!links.isNullOrEmpty()) {
                                items(links.size) { index ->
                                    val item = links[index]
                                    if (item.isNotBlank()) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(10.dp).fillMaxWidth().clickable {
                                                uriHandler.openUri(item)
                                            }

                                        ) {
                                            Text(text = item)
                                            Icon(
                                                Icons.Filled.KeyboardArrowDown,
                                                contentDescription = "arrow-down",
                                                modifier = Modifier.rotate(270f),
                                                tint = Color.White
                                            )
                                        }
                                        HorizontalDivider()
                                    }

                                }
                            }
                        }

                    }

                    val group = listOf(descriptionItem, linksItem, historyItem)
                    AccordionGroup(modifier = Modifier.padding(top = 8.dp), group = group)
                }

                DetailState.DefaultState -> Text(text = "default")
                is DetailState.ErrorState -> ErrorPage((state as DetailState.ErrorState).error)
                DetailState.LoadingState -> LoadingPage()
            }
        }
    }
}


