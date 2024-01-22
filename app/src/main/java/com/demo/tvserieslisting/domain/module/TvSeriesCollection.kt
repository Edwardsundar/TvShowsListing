package com.demo.tvserieslisting.domain.module

data class TvSeriesCollection(
    val adult: Boolean,
    val backdropPath: String,
    val firstAirDate: String,
    val id: Int,
    val name: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)