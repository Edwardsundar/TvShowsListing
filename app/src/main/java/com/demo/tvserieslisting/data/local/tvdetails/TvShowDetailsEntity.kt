package com.demo.tvserieslisting.data.local.tvdetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.demo.tvserieslisting.data.local.ProductionCompanyConverter
import com.demo.tvserieslisting.data.local.SeasonListConverter
import com.demo.tvserieslisting.data.local.StringListConverter
import com.demo.tvserieslisting.domain.module.Network
import com.demo.tvserieslisting.domain.module.ProductionCompany
import com.demo.tvserieslisting.domain.module.Season

@Entity
data class TvShowDetailsEntity(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    @TypeConverters(StringListConverter::class)
    val createdBy: List<String>,
    val firstAirDate: String,
    @TypeConverters(StringListConverter::class)
    val genres: List<String>,   // gender name
    val homepage: String,
    val inProduction: Boolean,
    val name: String,
    val networks: List<Network>,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    @TypeConverters(ProductionCompanyConverter::class)
    val productionCompanies: List<ProductionCompany>,
    @TypeConverters(StringListConverter::class)
    val productionCountries: List<String>,     // only name
    @TypeConverters(SeasonListConverter::class)
    val seasons: List<Season>,
    @TypeConverters(StringListConverter::class)
    val spokenLanguages: List<String>,   // only name or english name
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int
)
