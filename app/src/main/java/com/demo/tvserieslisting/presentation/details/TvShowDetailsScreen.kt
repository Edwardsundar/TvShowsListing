package com.demo.tvserieslisting.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.demo.tvserieslisting.R
import com.demo.tvserieslisting.common.Constants
import com.demo.tvserieslisting.domain.module.Creator
import com.demo.tvserieslisting.domain.module.Network
import com.demo.tvserieslisting.domain.module.ProductionCompany
import com.demo.tvserieslisting.domain.module.Season
import com.demo.tvserieslisting.ui.theme.DarkGray

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
                                contentDescription = state.value.tvShowDetails?.name
                            )
                        }
                        Card(
                            modifier = Modifier
                                .height(250.dp)
                                .weight(1f)
                                .border(
                                    width = 0.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    color = Color.Transparent
                                ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                        ){
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(6.dp)
                            ) {
                                Text(
                                    text = state.value.tvShowDetails?.name ?: "",
                                    color = MaterialTheme.colorScheme.inversePrimary,
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "TMDB Rating:",
                                    modifier = Modifier.fillMaxWidth(),
                                    fontWeight = FontWeight.Light,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = state.value.tvShowDetails?.voteAverage.toString() + "/10",
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Release Date:",
                                )
                                Text(
                                    text = state.value.tvShowDetails?.firstAirDate ?: Constants.UNKNOWN,
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Popularity:",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = state.value.tvShowDetails?.popularity.toString() ,
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = if(state.value.tvShowDetails?.inProduction == true) "Currently In Production"
                        else "Currently Not in Production ",
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Overview:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                    Text(
                        text = state.value.tvShowDetails?.overview ?: "",
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Created By: ",
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                    LazyRow(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        items(state.value.tvShowDetails?.createdBy ?: emptyList()){creator->
                            ProfileSection(creator)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Genres:",
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = state.value.tvShowDetails?.genres?.joinToString(separator = ", ") ?: "",
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Number Of Episodes ${state.value.tvShowDetails?.numberOfEpisodes}",
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Number Of Seasons ${state.value.tvShowDetails?.numberOfSeasons}",
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontWeight = FontWeight.Bold
                    )
                    // productionCountries spokenLanguages
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Spoken Languages: ",
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = state.value.tvShowDetails?.spokenLanguages?.joinToString(separator = ", ") ?: "",
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Production Countries: ",
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = state.value.tvShowDetails?.productionCountries?.joinToString(separator = ", ") ?: "",
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Seasons",
                        color = MaterialTheme.colorScheme.inversePrimary,
                    )
                    LazyRow {
                        itemsIndexed(state.value.tvShowDetails?.seasons ?: emptyList()){index,season->
                            SeasonSection(season , index)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Production Companies:",
                        color = MaterialTheme.colorScheme.inversePrimary,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column {
                        state.value.tvShowDetails?.productionCompanies?.forEachIndexed { index, companie ->
                            CompanySection(companie)
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Networks:",
                        color = MaterialTheme.colorScheme.inversePrimary,
                    )
                    Column {
                        state.value.tvShowDetails?.networks?.forEachIndexed { index: Int, network: Network ->
                            NetworkSection(network)
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NetworkSection(
    network: Network
){
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(200.dp)
            .padding(start = 8.dp)
    ) {
        AsyncImage(
            model = Constants.IMAGE_BASE_URL + network.logoPath,
            contentDescription = network.name
        )
        Text(
            text = network.name ?: "",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.inversePrimary
        )
    }
}

@Composable
fun CompanySection(
    company: ProductionCompany
){
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(200.dp)
            .padding(start = 8.dp ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (company.logoPath != null){
            AsyncImage(
                model = Constants.IMAGE_BASE_URL+company.logoPath,
                contentDescription = company.name
            )
        }
        Text(
            text = company.name ?: "",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.inversePrimary
        )

    }
}

@Composable
fun SeasonSection(
    season: Season,
    i : Int
){

    Box(
        modifier = Modifier
            .height(170.dp)
            .width(100.dp)
            .padding(end = 8.dp )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        ){
            if (season.posterPath != null) {
                AsyncImage(
                    model =  Constants.IMAGE_BASE_URL+season.posterPath,
                    contentDescription = season.name
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.no_poster_available),
                    contentDescription = season.name
                )
            }
        }
    }

}

@Composable
fun ProfileSection(
    creator: Creator
){

    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(end = 8.dp)
    ){
        Box(
            modifier = Modifier
                .clip(CircleShape)
        ){
            if(creator.profilePath != null ){
                AsyncImage(
                    model = Constants.IMAGE_BASE_URL + (creator.profilePath ),
                    contentDescription = creator.name,
                )
            }
            else {
                Image(
                    painter = painterResource(R.drawable.unknown_profile),
                    contentDescription = "Unknown user"
                )
            }
        }
        Text(
            text = creator.name ?: "Unknown",
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.inversePrimary
        )
    }

}