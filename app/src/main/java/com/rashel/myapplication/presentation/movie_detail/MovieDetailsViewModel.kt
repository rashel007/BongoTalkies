package com.rashel.myapplication.presentation.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rashel.myapplication.common.Resource
import com.rashel.myapplication.domain.model.movie_detail.MovieDetail
import com.rashel.myapplication.domain.use_case.get_movie.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val useCase: GetMovieUseCase) :
    ViewModel() {

    private val _movie = MutableLiveData<MovieDetail>()
    val movie: LiveData<MovieDetail>
        get() = _movie

    fun getMovie(id: Int) {

        useCase.invoke("c37d3b40004717511adb2c1fbb15eda4", id).onEach {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    it.data.let {
                        _movie.value = it
                    }

                }
                is Resource.Failed -> {

                }
            }
        }.launchIn(viewModelScope)
    }

}