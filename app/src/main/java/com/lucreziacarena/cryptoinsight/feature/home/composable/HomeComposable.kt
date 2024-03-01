package com.lucreziacarena.cryptoinsight.feature.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.*
import coil.request.ImageRequest
import com.lucreziacarena.cryptoinsight.feature.home.contract.HomeAction
import com.lucreziacarena.cryptoinsight.feature.home.contract.HomeEvent
import com.lucreziacarena.cryptoinsight.feature.home.contract.HomeState
import com.lucreziacarena.cryptoinsight.feature.home.viewmodel.HomeViewModel
import com.lucreziacarena.cryptoinsight.navigation.NavigationItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController

) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.event.collect {event->
                when(event){
                    is HomeEvent.NavigateToDetail -> navController.navigate("${NavigationItem.Detail.route}?id=${event.cryptoId}")
                }
            }
        }
    }
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(
                    paddingValues
                )
        ) {

            when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
                is HomeState.Content -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                            .padding(
                                horizontal = 8.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                        items((state as HomeState.Content).content.size) { index ->
                            val item=(state as HomeState.Content).content[index]
                            Card(
                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                shape = RoundedCornerShape(20.dp),
                                onClick = {
                                    viewModel.action(HomeAction.OnCryptoClick(cryptoId = item.id!!))
                                }
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(10.dp)

                                ) {

                                    val model = ImageRequest.Builder(LocalContext.current)
                                        .data(item.image)

                                        .crossfade(true)
                                        .build()
                                    val painter = rememberAsyncImagePainter(model)
                                    Image(
                                        modifier = Modifier.clip(CircleShape).size(150.dp),
                                        painter = painter,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                    )

                                    /*AsyncImage(
                                        model= item.image,
                                        contentDescription = null,
                                        //placeholder = painterResource(R.drawable.placeholder),
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.clip(CircleShape)
                                        )*/
                                    Text(text = item.name ?: "")
                                    Text(text = (item.current_price.toString() + "$") ?: "")
                                }
                            }
                        }
                    }
                }

                HomeState.DefaultState -> Text(text = "default")
                HomeState.ErrorState -> Text(text = "error")
                HomeState.LoadingState -> Text(text = "loading")
            }
        }
    }
}


