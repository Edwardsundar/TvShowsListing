package com.demo.tvserieslisting.presentation

sealed class UiEvent{

    data class SearchTvShowsByName( val name : String ) : UiEvent()

    object Refresh : UiEvent()

}
