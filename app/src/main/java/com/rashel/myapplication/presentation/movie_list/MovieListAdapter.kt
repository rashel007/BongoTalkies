package com.rashel.myapplication.presentation.movie_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rashel.myapplication.R
import com.rashel.myapplication.databinding.ItemMovieBinding
import com.rashel.myapplication.domain.model.movie_item.Movie
import javax.inject.Inject

class MovieListAdapter(diffCallback: DiffUtil.ItemCallback<Movie>) :
    PagingDataAdapter<Movie, MovieListAdapter.MovieViewModel>(diffCallback) {

    override fun onBindViewHolder(holder: MovieViewModel, position: Int) {
        Log.d("TEST", "onBindViewHolder $position")
        getItem(position)?.let {
            holder.textView.text = "${it.id}"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewModel(view)
    }

    inner class MovieViewModel(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textView)
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