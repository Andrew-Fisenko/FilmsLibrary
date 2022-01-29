package com.example.filmslibrary.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.DetailsFragmentBinding
import com.example.filmslibrary.model.AppState
import com.example.filmslibrary.model.entities.FilmCard
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<FilmCard>(BUNDLE_EXTRA)?.let {
            with(binding) {
                val film = it.film

                movieLengthTitle.text = getString(R.string.movie_length)
                filmYearTitle.text = getString(R.string.year)
                ratingIMDbTitle.text = getString(R.string.rating)
                filmName.text = film.filmName
                movieLength.text = it.film.idKinopoisk.toString()

                viewModel.filmLiveData.observe(viewLifecycleOwner, { appState ->
                    when (appState) {
                        is AppState.Error -> {
                            mainView.visibility = View.INVISIBLE
                            progressBar.visibility = View.GONE
                            error.visibility = View.VISIBLE
                        }
                        AppState.Loading -> {
                            mainView.visibility = View.INVISIBLE
                            progressBar.visibility = View.VISIBLE
                        }
                        is AppState.Success -> {
                            progressBar.visibility = View.GONE
                            mainView.visibility = View.VISIBLE
                            filmYear.text = appState.filmData[0].year.toString()
                            ratingIMDb.text = appState.filmData[0].ratingIMDb.toString()
                            description.text = appState.filmData[0].description
                        }
                    }
                })
            }
            viewModel.loadData(it.film.idKinopoisk)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "film"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
