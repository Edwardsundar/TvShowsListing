package com.demo.tvserieslisting.presentation.listing

import androidx.paging.PagingData
import com.demo.tvserieslisting.domain.module.TvSeriesCollection
import kotlinx.coroutines.flow.Flow

data class TvShowsListingState(
    val tvShowList: Flow<PagingData<List<TvSeriesCollection>>>? = null,
)
