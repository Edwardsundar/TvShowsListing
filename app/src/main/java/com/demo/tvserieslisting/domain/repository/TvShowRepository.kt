package com.demo.tvserieslisting.domain.repository

import com.demo.tvserieslisting.domain.module.TvShowDetails

interface TvShowRepository {

    suspend fun getLocalStoredTvShow(id : Int) : TvShowDetails?

    suspend fun getRemoteStoredTvShow(id : Int) : TvShowDetails

    suspend fun insertTvShowDetails( tvShowDetails: TvShowDetails )
}