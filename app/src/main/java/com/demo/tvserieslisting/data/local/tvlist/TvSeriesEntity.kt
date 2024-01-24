package com.demo.tvserieslisting.data.local.tvlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.demo.tvserieslisting.data.local.StringListConverter

@Entity
data class TvSeriesEntity(
    @PrimaryKey
    val id: Int,
    val adult: Boolean?,
    val backdropPath: String?,
    val firstAirDate: String?,
    val name: String?,
    @TypeConverters(StringListConverter::class)
    val originCountry: List<String>,
    val originalLanguage: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val voteAverage: Double?,
    val voteCount: Int
)

