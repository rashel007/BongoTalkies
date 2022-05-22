package com.rashel.myapplication.domain.use_case.get_movies

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rashel.myapplication.common.Resource
import com.rashel.myapplication.data.remote.dto.MovieDto
import com.rashel.myapplication.data.remote.dto.toMovies
import com.rashel.myapplication.domain.model.movie_item.Movie
import com.rashel.myapplication.domain.model.movie_item.Movies
import com.rashel.myapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(
        apiKey: String,
        language: String
    ): Flow<PagingData<MovieDto>> {
        return repository.getMovies(apiKey, language)
    }

}