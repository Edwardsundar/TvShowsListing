package com.demo.tvserieslisting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.tvserieslisting.common.Constants
import com.demo.tvserieslisting.presentation.Navigation
import com.demo.tvserieslisting.presentation.details.TvShowDetailsScreen
import com.demo.tvserieslisting.presentation.listing.TvShowListingScreen
import com.demo.tvserieslisting.presentation.search.TvShowSearchScreen
import com.demo.tvserieslisting.ui.theme.TvSeriesListingTheme
import com.google.gson.internal.GsonBuildConfig
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TvSeriesListingTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Navigation.TvShowsListingScreen.route
                ) {
                    composable(route = Navigation.TvShowsListingScreen.route) {
                        TvShowListingScreen(
                            navController = navController
                        )
                    }
                    composable(
                        route = Navigation.TvShowDetailScreen.route + "/{${Constants.TV_SHOW_Id}}"
                    ) {
                        TvShowDetailsScreen()
                    }
                    composable(route = Navigation.TvShowsSearchScreen.route) {
                        TvShowSearchScreen(
                            navController = navController
                        )
                    }
                }

            }
        }
    }
}
