package com.demo.tvserieslisting.data.remote.dto.tvlist

data class TvSeriesDto(
    val page: Int,
    val results: List<ResultDto>,
    val total_pages: Int,
    val total_results: Int
)