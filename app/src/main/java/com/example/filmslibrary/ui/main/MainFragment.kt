package com.example.filmslibrary.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.filmslibrary.R
import com.example.filmslibrary.model.entities.FilmCard
import com.google.android.material.snackbar.Snackbar
import com.example.filmslibrary.databinding.MainFragmentBinding
import com.example.filmslibrary.model.AppState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val observer = Observer<AppState>{ renderData(it)}
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getFilm()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding){
        when (appState){
            is AppState.Success -> {
                val filmData = appState.filmData
                progressBar.visibility = View.GONE
                weatherGroup.visibility = View.VISIBLE
                setData(filmData)

            }
            is AppState.Loading -> {
                weatherGroup.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                progressBar.visibility = View.GONE
                weatherGroup.visibility = View.INVISIBLE
                Snackbar
                    .make(mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") {viewModel.getFilm()}
                    .show()

            }
        }
    }

    private fun setData(filmData: FilmCard) = with(binding) {
        filmName.text = filmData.film.filmName
        filmYear.text = filmData.film.year.toString()
        movieLength.text = filmData.film.movieLength.toString()
        ratingIMDb.text = filmData.film.ratingIMDb.toString()
        description.text = filmData.film.description


    }

    companion object {
        fun newInstance() = MainFragment()
    }
}