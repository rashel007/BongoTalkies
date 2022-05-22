package com.rashel.myapplication.presentation.movie_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rashel.myapplication.common.Constants
import com.rashel.myapplication.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {


    private val viewModel by viewModels<MovieDetailsViewModel>()

    private val binding: FragmentMovieDetailBinding by lazy {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }
    private val args: MovieDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("PAGE", "MovieDetailFragment-onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("PAGE", "args ${args.movieId}")


        viewModel.getMovie(args.movieId)

        viewModel.loading.observe(viewLifecycleOwner) {
            Log.d("PAGE", "Loading ${it}")
            if (it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        }

        viewModel.movie.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load("${Constants.BASE_URL_IMAGE}w300/${it.backdrop_path}")
                .into(binding.ivBanner)

            Glide.with(requireContext())
                .load("${Constants.BASE_URL_IMAGE}w185/${it.poster_path}")
                .into(binding.ivPosterIamge)

            binding.tvMovieName.text = it.original_title
            binding.tvReleaseDate.text = it.release_date
            binding.tvVote.text = "${it.vote_average}"
            binding.tvTagLine.text = it.tagline
            binding.tvOverview.text = it.overview


        }

    }
}