package com.rashel.myapplication.data.remote.dto

import com.rashel.myapplication.domain.model.movie_item.Movie
import com.rashel.myapplication.domain.model.movie_item.Movies

data class MoviesDto(
    val page: Int,
    val results: List<MovieDto>
)

data class MovieDto(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun MoviesDto.toMovies(): Movies {
    return Movies(
        page = page,
        result = results.map { it.toMovie() }
    )
}

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        poster_path = poster_path
    )
}