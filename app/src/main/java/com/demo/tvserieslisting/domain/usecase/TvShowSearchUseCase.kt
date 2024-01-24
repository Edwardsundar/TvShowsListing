package com.demo.tvserieslisting.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListEntity
import com.demo.tvserieslisting.data.paging.SearchPagingSource
import com.demo.tvserieslisting.data.remote.TheMovieDatabaseApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowSearchUseCase @Inject constructor(
    private val api : TheMovieDatabaseApi
){

    operator fun invoke(query : String ) : Flow<PagingData<TvSeriesListEntity>> {
        return Pager(
            config = PagingConfig(1),
            pagingSourceFactory = {
                SearchPagingSource(
                    api = api,
                    query = query
                )
            }
        ).flow
    }
}