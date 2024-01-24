package com.demo.tvserieslisting.data.local.tvlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.demo.tvserieslisting.data.local.TvSeriesEntityConverter
import com.demo.tvserieslisting.data.remote.dto.tvlist.ResultDto

data class TvSeriesListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val page: Int,
    @TypeConverters(TvSeriesEntityConverter::class)
    val results: List<TvSeriesEntity>,
    val totalPages: Int
)
