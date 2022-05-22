package com.rashel.myapplication.presentation.movie_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rashel.myapplication.R
import com.rashel.myapplication.common.Constants
import com.rashel.myapplication.databinding.ItemMovieBinding
import com.rashel.myapplication.domain.model.movie_item.Movie
import javax.inject.Inject

class MovieListAdapter(
    diffCallback: DiffUtil.ItemCallback<Movie>,
    private val onItemClick: (Int) -> (Unit)
) :
    PagingDataAdapter<Movie, MovieListAdapter.MovieViewModel>(diffCallback) {

    override fun onBindViewHolder(holder: MovieViewModel, position: Int) {
        Log.d("TEST", "onBindViewHolder $position")
        getItem(position)?.let { movie ->
            movie.poster_path?.let { poster ->
                Glide.with(holder.itemView.context)
                    .load("${Constants.BASE_URL_IMAGE}w185/${poster}")
                    .placeholder(R.drawable.img_placeholder)
                    .into(holder.imgView)

                holder.itemView.setOnClickListener {
                    onItemClick(movie.id!!)
                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewModel(view)
    }

    inner class MovieViewModel(view: View) : RecyclerView.ViewHolder(view) {
        val imgView = view.findViewById<ImageView>(R.id.imgView)
    }

}

object UserComparator : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}