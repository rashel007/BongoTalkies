package com.rashel.myapplication.domain.model.movie_item


data class Movies(
    val page: Int?,
    val result: List<Movie>?
)

data class Movie(
    val id: Int?,
    val poster_path: String?
)
