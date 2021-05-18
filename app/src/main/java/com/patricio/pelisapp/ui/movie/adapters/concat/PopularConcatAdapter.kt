package com.patricio.pelisapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patricio.pelisapp.core.BaseConcatHolder
import com.patricio.pelisapp.databinding.PopularMoviesRowBinding
import com.patricio.pelisapp.ui.movie.adapters.MovieAdapter

class PopularConcatAdapter(private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = PopularMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ConcatViewHolder(itemBinding)
    }

    override fun getItemCount(): Int =1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }

    }

    private inner class ConcatViewHolder(val binding: PopularMoviesRowBinding):BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvPopularMovies.adapter = adapter
        }

    }
}