package com.demo.tvserieslisting.presentation.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesEntity
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListEntity
import com.demo.tvserieslisting.data.mappers.toTvSeriesCollection
import com.demo.tvserieslisting.data.mappers.toTvSeriesListEntity
import com.demo.tvserieslisting.data.remote.TheMovieDatabaseApi
import com.demo.tvserieslisting.domain.usecase.TvShowDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TvListingViewModel @Inject constructor(
    pager : Pager<Int,TvSeriesEntity>,
    private val tvShowDetailsUseCase: TvShowDetailsUseCase,
    private val api : TheMovieDatabaseApi
) : ViewModel() {

    private val tvShowPagingFlow = pager
        .flow
        .map { pagingData->
            pagingData.map {
                it.toTvSeriesCollection()
            }
        }
        .cachedIn(viewModelScope)

    val state = MutableStateFlow((TvShowsListingState(
        tvShowList = tvShowPagingFlow
    )))

    init {
//        state.value = state.value.copy(
//            tvShowList = tvShowPagingFlow
//        )
    }

    fun onEvent(){
        viewModelScope.launch {
            val shows = api.getPopularTvShows(1)
            val res = shows.toTvSeriesListEntity()
            val res2 = res.results.map { it.toTvSeriesCollection() }
            val a = 0

        }
    }

}