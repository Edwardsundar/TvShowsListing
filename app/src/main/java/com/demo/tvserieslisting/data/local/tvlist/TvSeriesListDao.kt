package com.demo.tvserieslisting.data.local.tvlist

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TvSeriesListDao {

    @Upsert
    suspend fun upsert(tvSeriesList : TvSeriesListEntity )

    @Query("SELECT * FROM tvserieslistentity")
    fun pagingSource() : PagingSource<Int , TvSeriesListEntity>

    @Query("DELETE FROM tvserieslistentity")
    suspend fun clearAll()
}