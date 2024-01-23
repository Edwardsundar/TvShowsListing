package com.demo.tvserieslisting.presentation.details

import com.demo.tvserieslisting.domain.module.TvShowDetails

data class TvSHowDetailsState(
    val error : String? = null,
    val isLoading : Boolean = false,
    val tvShowDetails: TvShowDetails? = null
)
