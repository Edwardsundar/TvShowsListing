package com.demo.tvserieslisting.data.local

import androidx.room.TypeConverter
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesEntity
import com.demo.tvserieslisting.domain.module.ProductionCompany
import com.demo.tvserieslisting.domain.module.Season
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TvSeriesEntityConverter{
    @TypeConverter
    fun fromTvSeriesList(tvSeriesList : List<TvSeriesEntity>): String {
        val gson = Gson()
        return gson.toJson(tvSeriesList)
    }

    @TypeConverter
    fun toTvSeriesList(str : String): List<TvSeriesEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<TvSeriesEntity>>() {}.type
        return gson.fromJson(str, type)
    }
}

class StringListConverter {
    @TypeConverter
    fun fromList(list : List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(str : String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(str, type)
    }
}


class SeasonListConverter {
    @TypeConverter
    fun fromSeason(season : List<Season>): String {
        val gson = Gson()
        return gson.toJson(season)
    }

    @TypeConverter
    fun toSeason(season : String): List<Season> {
        val gson = Gson()
        val type = object : TypeToken<List<Season>>() {}.type
        return gson.fromJson(season, type)
    }
}


class ProductionCompanyConverter {
    @TypeConverter
    fun fromProductionCompany(productionCompany : List<ProductionCompany>): String {
        val gson = Gson()
        return gson.toJson(productionCompany)
    }

    @TypeConverter
    fun toProductionCompany(productionCompany : String): List<ProductionCompany> {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompany>>() {}.type
        return gson.fromJson(productionCompany, type)
    }
}