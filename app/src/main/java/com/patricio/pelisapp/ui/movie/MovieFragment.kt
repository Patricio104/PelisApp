package com.patricio.pelisapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.patricio.pelisapp.R
import com.patricio.pelisapp.core.Resource
import com.patricio.pelisapp.data.model.Movie
import com.patricio.pelisapp.data.remote.RemoteMovieDataSource
import com.patricio.pelisapp.databinding.FragmentMovieBinding
import com.patricio.pelisapp.presentation.MovieViewModel
import com.patricio.pelisapp.presentation.MovieViewModelFactory
import com.patricio.pelisapp.repository.MovieRepositoryImpl
import com.patricio.pelisapp.repository.RetrofitClient
import com.patricio.pelisapp.ui.movie.adapters.MovieAdapter
import com.patricio.pelisapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.patricio.pelisapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.patricio.pelisapp.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var binding: FragmentMovieBinding

    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }
            }
        })

        /*
        viewModel.fetchUpcomingMovies().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })

        viewModel.fetchPopularMovies().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveDataPopular", "Loading")
                }
                is Resource.Success -> {
                    Log.d("LiveDataPopular", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })

        viewModel.fetchTopRatedMovies().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveDataTopRated", "Loading")
                }
                is Resource.Success -> {
                    Log.d("LiveDataTopRated", "${result.data}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })*/
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_languaje,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}