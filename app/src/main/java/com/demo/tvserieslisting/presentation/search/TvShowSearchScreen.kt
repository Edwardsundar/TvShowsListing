package com.demo.tvserieslisting.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.demo.tvserieslisting.R
import com.demo.tvserieslisting.data.mappers.toTvSeriesCollection
import com.demo.tvserieslisting.presentation.Navigation
import com.demo.tvserieslisting.presentation.listing.ShowBox


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowSearchScreen(
    viewModel: TvSHowSearchViewModel = hiltViewModel(),
    navController: NavController
){

    val state = viewModel.state.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchBar {
                viewModel.onSearchEvent(it)
            }
        }
    ){paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ){
            items(state , key = {
                it.id
            }) {tvSeries->

                if(tvSeries != null){
                    ShowBox(
                        collection = tvSeries
                    ){
                        navController.navigate(Navigation.TvShowDetailScreen.route + "/${tvSeries.id}")
                    }
                }
            }

        }
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    onSearch :(String) -> Unit
){
    var query by remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
        value = query,
        onValueChange = {query = it},
        label = { Text("Search Tv Shows" , color = MaterialTheme.colorScheme.inversePrimary) },
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clickable {
                        keyboardController?.hide()
                        onSearch(query)
                    }
                    .focusRequester(focusRequester)
            )
        },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                onSearch(query)
            }
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    )
    Spacer(modifier = Modifier.height(16.dp))
}
