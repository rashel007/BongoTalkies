package com.rashel.myapplication.domain.use_case.get_movie

import com.rashel.myapplication.common.Resource
import com.rashel.myapplication.data.remote.dto.movie_detail.toMovieDetail
import com.rashel.myapplication.domain.model.movie_detail.MovieDetail
import com.rashel.myapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(apiKey: String, movieID: Int): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading)
            val movieDetail = repository.getMovie(apiKey, movieID).toMovieDetail()
            emit(Resource.Success(movieDetail))
        } catch (e: HttpException) {
            emit(Resource.Failed(message = e.localizedMessage ?: "Failed"))
        } catch (e: IOException) {
            emit(Resource.Failed(message = "Something went wrong"))
        }
    }
}