package com.rashel.myapplication.presentation.movie_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.rashel.myapplication.R
import com.rashel.myapplication.databinding.FragmentMovieListBinding
import com.rashel.myapplication.domain.model.movie_item.Movie
import com.rashel.myapplication.presentation.collectLast
import com.rashel.myapplication.presentation.movie_detail.MovieDetailFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel by viewModels<MovieListViewModel>()
    private val binding: FragmentMovieListBinding by lazy {
        FragmentMovieListBinding.inflate(layoutInflater)
    }

    val pagingAdapter = MovieListAdapter(UserComparator)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("PAGE", "MovieListFragment-onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = pagingAdapter

        collectLast(viewModel.item, ::setUsers)

    }


    private suspend fun setUsers(movieItem: PagingData<Movie>) {
        Log.d("TEST", "setUsers")
        pagingAdapter.submitData(movieItem)
    }
}