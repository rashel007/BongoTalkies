package com.rashel.myapplication.domain.model.movie_detail

data class MovieDetail(
    val id: Int?,
    val original_title: String?,
    val overview: String?,
    val backdrop_path: String?,
    val poster_path: String?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val vote_average: Double?,
)
