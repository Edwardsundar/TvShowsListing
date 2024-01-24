package com.demo.tvserieslisting.domain.module

import androidx.compose.runtime.Immutable
import com.demo.tvserieslisting.data.remote.dto.tvdetails.GenreDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.LastEpisodeToAirDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.NetworkDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.ProductionCompanyDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.ProductionCountryDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.SeasonDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.SpokenLanguageDto

@Immutable
data class TvShowDetails(
    val adult: Boolean?,
    val backdropPath: String?,
    val createdBy: List<Creator>,
    val firstAirDate: String?,
    val genres: List<String>,   // gender name
    val homepage: String?,
    val id: Int,
    val inProduction: Boolean?,
    val name: String?,
    val networks: List<Network>,
    val numberOfEpisodes: Int?,
    val numberOfSeasons: Int?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<String>,     // only name
    val seasons: List<Season>,
    val spokenLanguages: List<String>,   // only name or english name
    val status: String?,
    val tagline: String?,
    val type: String?,
    val voteAverage: Double?,
    val voteCount: Int?
)
