package com.demo.tvserieslisting.data.local.tvlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


data class TvSeriesEntity(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val firstAirDate: String,
    val name: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)

