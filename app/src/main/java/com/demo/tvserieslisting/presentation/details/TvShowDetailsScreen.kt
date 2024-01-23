package com.demo.tvserieslisting.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.demo.tvserieslisting.common.Constants

@Composable
fun TvShowDetailsScreen(
    viewModel: TvShowDetailViewModel = hiltViewModel<TvShowDetailViewModel>()
){

    val state = viewModel.state.collectAsState()

    if (state.value.isLoading){
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            CircularProgressIndicator()
        }
    }else if(state.value.error != null){
        /* TODO Show Error Screen With Error MEssage */
    }else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    AsyncImage(
                        model = ( Constants.IMAGE_BASE_URL + state.value.tvShowDetails?.backdropPath),
                        contentDescription = state.value.tvShowDetails?.name ,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp , end = 16.dp , bottom = 16.dp , top = 200.dp)
                    ){
                        Box(
                            modifier = Modifier
                                .height(250.dp)
                                .weight(1f)
                                .border(
                                    width = 0.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    color = Color.Transparent
                                )
                        ){
                            AsyncImage(
                                model =Constants.IMAGE_BASE_URL + state.value.tvShowDetails?.posterPath,
                                contentDescription = state.value.tvShowDetails?.name,
                                modifier = Modifier
                                    .border(
                                        width = 0.dp,
                                        shape = RoundedCornerShape(10.dp),
                                        color = Color.Transparent
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(250.dp)
                                .weight(1f)
                                .border(
                                    width = 0.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    color = Color.Transparent
                                )
                        ){
                            Column {
                                Text(
                                    text = state.value.tvShowDetails?.name ?: "",
                                    fontWeight = FontWeight.ExtraBold,
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "TMDB Rating " + state.value.tvShowDetails?.voteAverage + "/10",
                                    modifier = Modifier.fillMaxWidth(),
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Release Date",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = state.value.tvShowDetails?.firstAirDate ?: Constants.UNKNOWN,
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Popularity",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = state.value.tvShowDetails?.popularity.toString() ,
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = if(state.value.tvShowDetails?.inProduction == true) "Currently In Production"
                                    else "Currently Not in Production ",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = state.value.tvShowDetails?.overview ?: "",
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }

}