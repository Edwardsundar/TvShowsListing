package com.demo.tvserieslisting.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.tvserieslisting.common.Constants
import com.demo.tvserieslisting.common.Resource
import com.demo.tvserieslisting.domain.module.TvShowDetails
import com.demo.tvserieslisting.domain.usecase.TvShowDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val tvShowDetailsUseCase: TvShowDetailsUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    val state = MutableStateFlow(TvSHowDetailsState())

    init {

        var showId = "1"
        savedStateHandle.get<String>(Constants.TV_SHOW_Id)?.let { id->
            if (id.isNotBlank()){
                showId = id
            }
        }
        tvShowDetailsUseCase.invoke(showId.toInt()).onEach { response->

            when(response){
                is Resource.Error -> {
                    state.value = TvSHowDetailsState(
                        error = response.message ?: ""
                    )
                }
                is Resource.Loading -> {
                    state.value = TvSHowDetailsState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    state.value = TvSHowDetailsState(
                        tvShowDetails = response.data
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}