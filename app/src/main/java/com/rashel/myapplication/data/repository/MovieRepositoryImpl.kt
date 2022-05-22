package com.rashel.myapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rashel.myapplication.common.Resource
import com.rashel.myapplication.data.data_source.MovieListDataSource
import com.rashel.myapplication.data.remote.TmdbApiService
import com.rashel.myapplication.data.remote.dto.MovieDto
import com.rashel.myapplication.data.remote.dto.MoviesDto
import com.rashel.myapplication.data.remote.dto.movie_detail.MovieDetailDto
import com.rashel.myapplication.domain.model.movie_item.Movie
import com.rashel.myapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TmdbApiService
) : MovieRepository {


    override suspend fun getMovie(apiKey: String, movieID: Int): MovieDetailDto {
        return apiService.getMovie(movieID, apiKey)
    }

    override fun getMovies(
        apiKey: String,
        language: String,
    ): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MovieListDataSource(apiService, apiKey, language) }
        ).flow
    }

}