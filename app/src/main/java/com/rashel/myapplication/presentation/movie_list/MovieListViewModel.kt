package com.rashel.myapplication.presentation.movie_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.rashel.myapplication.data.remote.dto.toMovie
import com.rashel.myapplication.domain.use_case.get_movies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val item = getMoviesUseCase.invoke("c37d3b40004717511adb2c1fbb15eda4", "end", 1).map {
        it.map {
            Log.d("TEST", "it.toMovie()")
            it.toMovie()
        }
    }.cachedIn(viewModelScope)

}