package com.demo.tvserieslisting.presentation.listing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.demo.tvserieslisting.R
import com.demo.tvserieslisting.common.Constants
import com.demo.tvserieslisting.domain.module.TvSeriesCollection


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShowBox(
    collection : TvSeriesCollection,
    onCLick : ()->Unit
){

    Card(
        onClick = {
                  onCLick()
        },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            focusedElevation = 20.dp,
            hoveredElevation = 30.dp,
            draggedElevation = 40.dp
        ),
        border = BorderStroke(1.dp , MaterialTheme.colorScheme.onSurface),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp , end = 10.dp , top = 8.dp , bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Box(
                modifier = Modifier
                    .weight(1.2f)
                    .height(200.dp)
            ){
                AsyncImage(
                    model = (Constants.IMAGE_BASE_URL + collection.posterPath) ?: "",
                    contentDescription = collection.name,

                )
                if(collection.adult != false){
                    Image(
                        painter = painterResource(R.drawable.kindpng_6854865),
                        contentDescription = "18+logo",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(30.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))
            Column (
                modifier = Modifier
                    .weight(2.8f)
            ){
                Text(
                    text = collection.name ?: "",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "From: "+(collection.firstAirDate ?: Constants.UNKNOWN),
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = collection.overview ?: "",
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "TMDB Rating " + collection.voteAverage + "/10",
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Light,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun CircularIndicator(){

}