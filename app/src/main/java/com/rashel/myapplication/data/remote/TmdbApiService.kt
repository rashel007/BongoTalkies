package com.rashel.myapplication.data.remote

import com.rashel.myapplication.data.remote.dto.MoviesDto
import com.rashel.myapplication.data.remote.dto.movie_detail.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("movie/top_rated")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MoviesDto


    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") api_key: String
    ): MovieDetailDto
}