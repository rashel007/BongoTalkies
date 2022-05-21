package com.rashel.myapplication.data.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rashel.myapplication.data.remote.TmdbApiService
import com.rashel.myapplication.data.remote.dto.MovieDto
import com.rashel.myapplication.data.remote.dto.toMovies
import com.rashel.myapplication.domain.model.movie_item.Movie
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_POSITION = 1

class MovieListDataSource(
    private val apiService: TmdbApiService,
    private val apiKey: String,
    private val language: String,
    private val page: Int
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {

        return try {
            val position = params.key ?: STARTING_POSITION
            val response = apiService.getMovies(apiKey, language, page)

            LoadResult.Page(
                data = response.results,
                prevKey = if (position == STARTING_POSITION) null else position - 1,
                nextKey = if (response.results.isNullOrEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }


}