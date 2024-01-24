package com.demo.tvserieslisting.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.log
import androidx.room.withTransaction
import com.demo.tvserieslisting.data.local.TvSeriesDatabase
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesEntity
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListEntity
import com.demo.tvserieslisting.data.mappers.toTvSeriesEntity
import com.demo.tvserieslisting.data.mappers.toTvSeriesListEntity
import com.demo.tvserieslisting.data.remote.TheMovieDatabaseApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TvSeriesRemoteMediator(
    private val tvSeriesDatabase: TvSeriesDatabase,
    private val theMovieDatabaseApi: TheMovieDatabaseApi
): RemoteMediator<Int,TvSeriesEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvSeriesEntity>
    ): MediatorResult {
        return try {

            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult
                        .Success( endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1  // there is no last item so its a first call to the api
                    else{
                      //val c = state.config.prefetchDistance
                        val e = state.pages.size
                        state.pages.size  + 1
                    }
                }
            }

            val tvSeriesLists = theMovieDatabaseApi.getPopularTvShows(page = loadKey)

            tvSeriesDatabase.withTransaction {
                if (loadType == LoadType.REFRESH){
                    tvSeriesDatabase.tvSeriesListDao().clearAll()
                }
                val tvSeriesListEntity = tvSeriesLists.results.map { it.toTvSeriesEntity() }
                tvSeriesDatabase.tvSeriesListDao().upsert(tvSeriesListEntity)
            }

            MediatorResult.Success(
                endOfPaginationReached = tvSeriesLists.results.isEmpty()
            )

        }catch (e : IOException){
            MediatorResult.Error(e)
        }catch (e : HttpException){
            MediatorResult.Error(e)
        }
    }

}