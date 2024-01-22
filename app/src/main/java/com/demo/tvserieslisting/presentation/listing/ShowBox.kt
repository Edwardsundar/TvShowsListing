package com.demo.tvserieslisting.presentation.listing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.demo.tvserieslisting.domain.module.TvSeriesCollection


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShowBox(
    collection : TvSeriesCollection,
    viewModel: TvListingViewModel
){

    Card(
        onClick = {

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
            .height(400.dp)
    ) {
        Column {
            AsyncImage(
                model = collection.posterPath,
                contentDescription = collection.name,
                modifier = Modifier
                    .weight(1f)
                    .height()
            )
        }
    }

}