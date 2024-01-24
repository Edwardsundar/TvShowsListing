package com.demo.tvserieslisting.presentation

sealed class Navigation(val route : String){

    object TvShowsListingScreen:Navigation("Tv_Shows_Listing_Screen")

    object TvShowDetailScreen : Navigation("Tv_Show_Details_Screen")

    object TvShowsSearchScreen : Navigation("Tv_Show_Search_Screen")

}
