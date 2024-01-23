package com.demo.tvserieslisting.data.remote

import com.demo.tvserieslisting.common.Constants
import com.demo.tvserieslisting.data.remote.dto.tvdetails.TvShowDetailsDto
import com.demo.tvserieslisting.data.remote.dto.tvlist.TvSeriesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMovieDatabaseApi {

    @GET("search/tv")
    suspend fun searchTvShowsByName(
        @Query("query") query : String,
        @Query("page") page : Int,
        @Query("api_key") api : String = Constants.API_KEY
    ) : TvSeriesDto

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int,
        @Query("api_key") api : String = Constants.API_KEY
        ) : TvSeriesDto

    @GET("tv/{seriesId}")
    suspend fun getTVShowById(
        @Path("seriesId") seriesId : String,
        @Query("api_key") api : String = Constants.API_KEY
        ) : TvShowDetailsDto

}