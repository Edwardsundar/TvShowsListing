package com.demo.tvserieslisting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demo.tvserieslisting.data.local.tvdetails.TvShowDetailsDao
import com.demo.tvserieslisting.data.local.tvdetails.TvShowDetailsEntity
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListDao
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListEntity


@Database(
    entities = [TvSeriesListEntity::class , TvShowDetailsEntity::class],
    version = 1
)
@TypeConverters(
    TvSeriesEntityConverter::class,
    ProductionCompanyConverter::class,
    SeasonListConverter::class,
    StringListConverter::class
)
abstract class TvSeriesDatabase : RoomDatabase() {

    abstract fun tvSeriesListDao() : TvSeriesListDao

    abstract fun tvShowDetailsDao() : TvShowDetailsDao

}