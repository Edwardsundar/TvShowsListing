package com.demo.tvserieslisting.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.demo.tvserieslisting.common.Constants
import com.demo.tvserieslisting.data.local.TvSeriesDatabase
import com.demo.tvserieslisting.data.local.tvdetails.TvShowDetailsDao
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListEntity
import com.demo.tvserieslisting.data.remote.TheMovieDatabaseApi
import com.demo.tvserieslisting.data.remote.TvSeriesRemoteMediator
import com.demo.tvserieslisting.data.repository.TvShowRepositoryImpl
import com.demo.tvserieslisting.domain.repository.TvShowRepository
import com.demo.tvserieslisting.domain.usecase.TvShowDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): TheMovieDatabaseApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDatabaseApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTvSeriesDatabase(@ApplicationContext context: Context): TvSeriesDatabase{
        return Room.databaseBuilder(
            context,
            TvSeriesDatabase::class.java,
            "book_mark_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTvShowPager(
        dataBase: TvSeriesDatabase,
        api: TheMovieDatabaseApi
    ) : Pager<Int , TvSeriesListEntity> {

        return Pager(
            config = PagingConfig(pageSize = 1),
            remoteMediator = TvSeriesRemoteMediator(
                dataBase,api
            ),
            pagingSourceFactory = {
                dataBase.tvSeriesListDao().pagingSource()
            }
        )

    }

    @Singleton
    @Provides
    fun provideTvShoDetailsDao(
        dataBase : TvSeriesDatabase
    ):TvShowDetailsDao{
        return dataBase.tvShowDetailsDao()
    }

    @Provides
    @Singleton
    fun provideTvShowRepository(
        api : TheMovieDatabaseApi,
        bookMarkDao : TvShowDetailsDao
    ):TvShowRepository{
        return TvShowRepositoryImpl(api , bookMarkDao )
    }

    @Provides
    @Singleton
    fun provideTvShowDetailsUseCase(
        repository: TvShowRepository
    ):TvShowDetailsUseCase {
        return TvShowDetailsUseCase(repository)
    }


}