package com.demo.tvserieslisting.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListEntity
import com.demo.tvserieslisting.data.mappers.toTvSeriesListEntity
import com.demo.tvserieslisting.data.remote.TheMovieDatabaseApi
import javax.inject.Inject

class SearchPagingSource (
    private val api : TheMovieDatabaseApi,
    private val query : String
) : PagingSource<Int , TvSeriesListEntity>() {
    override fun getRefreshKey(state: PagingState<Int, TvSeriesListEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, TvSeriesListEntity> {
        val currentPage = params.key ?: 1
        return try {
            val response = api.searchTvShowsByName(query = query , page = currentPage)
            val endPageIsReached  =response.results.isEmpty()
            if (!endPageIsReached){
                LoadResult.Page(
                    data = listOf(response.toTvSeriesListEntity()),
                    prevKey = if (currentPage == 1) null else currentPage -1,
                    nextKey = if (endPageIsReached) null else currentPage + 1
                )
            }else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}