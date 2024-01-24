package com.demo.tvserieslisting.data.local.tvlist

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TvSeriesListDao {

    @Upsert
    suspend fun upsert(tvSeriesList : List<TvSeriesEntity> )

    @Query("SELECT * FROM tvseriesentity")
    fun pagingSource() : PagingSource<Int , TvSeriesEntity>

    @Query("DELETE FROM tvseriesentity")
    suspend fun clearAll()
}