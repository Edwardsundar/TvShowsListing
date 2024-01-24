package com.demo.tvserieslisting.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesEntity
import com.demo.tvserieslisting.data.local.tvlist.TvSeriesListEntity
import com.demo.tvserieslisting.domain.module.TvSeriesCollection
import com.demo.tvserieslisting.domain.usecase.TvShowSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TvSHowSearchViewModel @Inject constructor(
    private val tvShowSearchUseCase: TvShowSearchUseCase
):ViewModel() {

    val state = MutableStateFlow<PagingData<TvSeriesCollection>>(PagingData.empty())
    fun onSearchEvent(query : String){
        viewModelScope.launch {
            tvShowSearchUseCase.invoke(query).cachedIn(viewModelScope).collect{
                state.value = it
            }
        }
    }
}