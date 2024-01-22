package com.demo.tvserieslisting.data.remote

import com.demo.tvserieslisting.common.Constants
import com.demo.tvserieslisting.data.remote.dto.tvdetails.TvShowDetailsDto
import com.demo.tvserieslisting.data.remote.dto.tvlist.TvSeriesDto
import retrofit2.http.GET
import retrofit2.http.Path


interface TheMovieDatabaseApi {

    @GET("search/tv?api_key=${Constants.API_KEY}&query={name}&page={page}")
    suspend fun searchTvShowsByName(
        @Path("name") name : String,
        @Path("page") page : Int
    ) : TvSeriesDto

    @GET("tv/popular?api_key=${Constants.API_KEY}&page={page}")
    suspend fun getPopularTvShows(@Path("page") page: Int) : TvSeriesDto

    @GET("tv/{seriesId}?api_key=${Constants.API_KEY}")
    suspend fun getTVShowById(@Path("seriesId") seriesId : String) : TvShowDetailsDto

}