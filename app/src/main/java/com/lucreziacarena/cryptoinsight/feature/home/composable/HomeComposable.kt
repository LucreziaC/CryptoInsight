package com.lucreziacarena.cryptoinsight.feature.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucreziacarena.cryptoinsight.feature.home.contract.HomeState
import com.lucreziacarena.cryptoinsight.feature.home.viewmodel.HomeViewModel

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    Scaffold() { paddingValues ->
        Box(
            modifier = Modifier
                .padding(
                    paddingValues
                )
        ) {

            when (val state = viewModel.state.collectAsStateWithLifecycle().value) {
                is HomeState.Content -> {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.fillMaxSize()
                            .padding(
                                horizontal = 8.dp
                            ),
                        columns = StaggeredGridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalItemSpacing = 4.dp,

                        ) {
                        items((state as HomeState.Content).content.size) { index ->
                            Text(text = (state as HomeState.Content).content[index].name ?: "")
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


