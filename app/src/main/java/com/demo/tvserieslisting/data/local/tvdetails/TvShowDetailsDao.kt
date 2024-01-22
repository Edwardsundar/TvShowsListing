package com.demo.tvserieslisting.data.local.tvdetails

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TvShowDetailsDao {

    @Upsert
    suspend fun upsertTvShowDetails( tvShowDetailsEntity: TvShowDetailsEntity )

    @Query("SELECT * FROM tvshowdetailsentity WHERE id = :id")
    fun getTvShowById(id : Int) : TvShowDetailsEntity?

    @Query("DELETE FROM tvshowdetailsentity")
    suspend fun deleteAllTvShowsDetails()

    @Query("DELETE FROM tvshowdetailsentity WHERE id = :id")
    suspend fun deleteTvShowDetailById( id : Int )

}