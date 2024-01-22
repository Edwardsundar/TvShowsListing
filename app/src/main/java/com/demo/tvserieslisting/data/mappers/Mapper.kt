package com.demo.tvserieslisting.data.mappers

import com.demo.tvserieslisting.data.local.tvdetails.TvShowDetailsEntity
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesEntity
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListEntity
import com.demo.tvserieslisting.data.remote.dto.tvdetails.GenreDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.NetworkDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.ProductionCompanyDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.SeasonDto
import com.demo.tvserieslisting.data.remote.dto.tvdetails.TvShowDetailsDto
import com.demo.tvserieslisting.data.remote.dto.tvlist.ResultDto
import com.demo.tvserieslisting.data.remote.dto.tvlist.TvSeriesDto
import com.demo.tvserieslisting.domain.module.Network
import com.demo.tvserieslisting.domain.module.ProductionCompany
import com.demo.tvserieslisting.domain.module.Season
import com.demo.tvserieslisting.domain.module.TvSeriesCollection
import com.demo.tvserieslisting.domain.module.TvShowDetails


fun TvSeriesDto.toTvSeriesListEntity() : TvSeriesListEntity {
    return TvSeriesListEntity(
        results = results.map { it.toTvSeriesEntity() },
        page = page,
        totalPages = total_pages
    )
}

fun ResultDto.toTvSeriesEntity() : TvSeriesEntity {
    return TvSeriesEntity(
        id = id,
        adult = adult,
        backdropPath = backdrop_path,
        firstAirDate = first_air_date,
        name = name,
        originCountry = origin_country,
        originalLanguage = original_language,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        voteAverage = vote_average,
        voteCount = vote_count
    )
}


fun TvSeriesEntity.toTvSeriesCollection() : TvSeriesCollection {
    return TvSeriesCollection(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        name = name,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun TvShowDetailsDto.toTvShowDetailsEntity() : TvShowDetailsEntity {
    return TvShowDetailsEntity(
        id = id,
        adult = adult,
        backdropPath = backdrop_path,
        createdBy = created_by,
        firstAirDate = first_air_date,
        genres = genres.map { it.name },
        homepage = homepage,
        inProduction =  in_production,
        name = name,
        networks = networks.map { it.toNetwork() },
        numberOfEpisodes = number_of_episodes,
        numberOfSeasons = number_of_seasons,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        productionCompanies = production_companies.map { it.toProductionCompanies() },
        productionCountries = production_countries.map { it.name },
        seasons = seasons.map { it.toSeasons() },
        spokenLanguages = spoken_languages.map { it.name },
        status = status,
        tagline = tagline,
        type = type,
        voteAverage = vote_average,
        voteCount = vote_count
    )
}

fun TvShowDetailsEntity.toTvShowDetails() : TvShowDetails {
    return TvShowDetails(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        createdBy = createdBy,
        firstAirDate = firstAirDate,
        genres = genres,
        homepage = homepage,
        inProduction =  inProduction,
        name = name,
        networks = networks,
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        seasons = seasons,
        spokenLanguages = spokenLanguages,
        status = status,
        tagline = tagline,
        type = type,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun SeasonDto.toSeasons() : Season {
    return  Season(
        airDate = air_date,
        episodeCount = episode_count,
        id = id,
        name = name,
        overview = overview,
        posterPath = poster_path,
        seasonNumber = season_number,
        voteAverage = vote_average
    )
}


fun ProductionCompanyDto.toProductionCompanies() : ProductionCompany{
    return  ProductionCompany(
        logoPath = logo_path,
        name = name,
        originCountry = origin_country
    )
}

fun NetworkDto.toNetwork() : Network {
    return Network(
        logoPath = logo_path,
        name = name,
        originCountry = origin_country
    )
}


