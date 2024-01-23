package com.demo.tvserieslisting.presentation.listing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.demo.tvserieslisting.presentation.Navigation
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowListingScreen(
    navController: NavController,
    viewModel: TvListingViewModel = hiltViewModel<TvListingViewModel>()
){

    val state = viewModel.state.collectAsState()

    val listing = state.value.tvShowList.collectAsLazyPagingItems()

    LaunchedEffect(key1 = listing.loadState){
        if (listing.loadState.refresh is LoadState.Error){
            /* TODO Handling Error */
        }
    }

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TvShows",
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
            )
        }
    ) { paddingValue ->

        if (listing.loadState.refresh is LoadState.Loading){
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                CircularProgressIndicator()
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValue)
            ) {
                items(listing){lists->



                    lists?.forEachIndexed { index, tvSeriesCollection ->
                        ShowBox(
                            lists[index]
                        ){
                            navController.navigate(Navigation.TvShowDetailScreen.route + "/${lists[index].id}")
                        }
                    }
                }
                item {
                    if (listing.loadState.append is LoadState.Loading){
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }




}
