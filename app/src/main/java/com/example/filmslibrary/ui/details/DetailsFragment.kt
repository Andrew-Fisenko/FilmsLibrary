package com.example.filmslibrary.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.DetailsFragmentBinding
import com.example.filmslibrary.model.entities.FilmCard


class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

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
                filmYear.text = it.film.year.toString()
                movieLength.text = it.film.movieLength.toString()
                ratingIMDb.text = it.film.ratingIMDb.toString()
                description.text = it.film.description
            }
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
