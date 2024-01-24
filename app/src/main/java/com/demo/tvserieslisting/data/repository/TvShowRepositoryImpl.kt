package com.demo.tvserieslisting.data.repository

import com.demo.tvserieslisting.data.local.tvdetails.TvShowDetailsDao
import com.demo.tvserieslisting.data.mappers.toTvShowDetails
import com.demo.tvserieslisting.data.mappers.toTvShowDetailsEntity
import com.demo.tvserieslisting.data.remote.TheMovieDatabaseApi
import com.demo.tvserieslisting.domain.module.TvShowDetails
import com.demo.tvserieslisting.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val api : TheMovieDatabaseApi,
    private val tvShowDetailsDao: TvShowDetailsDao
) : TvShowRepository {

    override suspend fun getLocalStoredTvShow(id: Int): TvShowDetails? {
        val show =  tvShowDetailsDao.getTvShowById(id)
        return show?.toTvShowDetails()
    }

    override suspend fun getRemoteStoredTvShow(id: Int): TvShowDetails {
        val show = api.getTVShowById(id.toString())
        return show.toTvShowDetailsEntity().toTvShowDetails()
    }

    override suspend fun insertTvShowDetails(tvShowDetails: TvShowDetails) {
        tvShowDetailsDao.upsertTvShowDetails(
            tvShowDetails.toTvShowDetailsEntity()
        )
    }

    override suspend fun searchTvShows(query: String) {

    }
}