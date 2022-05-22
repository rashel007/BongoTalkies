package com.rashel.myapplication.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.rashel.myapplication.common.Resource
import com.rashel.myapplication.data.remote.dto.MovieDto
import com.rashel.myapplication.data.remote.dto.MoviesDto
import com.rashel.myapplication.data.remote.dto.movie_detail.MovieDetailDto
import com.rashel.myapplication.domain.model.movie_item.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(apiKey: String, language: String): Flow<PagingData<MovieDto>>

    suspend fun getMovie(apiKey: String, movieID: Int): MovieDetailDto
}