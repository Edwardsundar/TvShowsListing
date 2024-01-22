package com.demo.tvserieslisting.presentation.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListEntity
import com.demo.tvserieslisting.data.mappers.toTvSeriesCollection
import com.demo.tvserieslisting.domain.usecase.TvShowDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class TvListingViewModel @Inject constructor(
    pager : Pager<Int,TvSeriesListEntity>,
    private val tvShowDetailsUseCase: TvShowDetailsUseCase
) : ViewModel() {

    val state = MutableStateFlow((TvShowsListingState()))

    init {
        val tvShowPagingFlow = pager
            .flow
            .map { pagingData->
                pagingData.map { tvSeriesListEntity->
                    tvSeriesListEntity.results.map { tvSeriesEntity ->
                        tvSeriesEntity.toTvSeriesCollection()
                    }
                }
            }
            .cachedIn(viewModelScope)
        state.value = state.value.copy(
            tvShowList = tvShowPagingFlow
        )
    }

}