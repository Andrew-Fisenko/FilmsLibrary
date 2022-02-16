import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.filmslibrary.R
import com.example.filmslibrary.databinding.DetailsFragmentBinding
import com.example.filmslibrary.model.entities.FilmCard
import com.example.filmslibrary.model.entities.getDefaultFilm
import com.example.filmslibrary.model.entities.getFilms
import com.example.filmslibrary.model.entities.rest_entities.FilmDTO
import com.example.filmslibrary.ui.details.DetailsService
import com.example.filmslibrary.ui.details.FILENAME_EXTRA
import com.example.filmslibrary.ui.details.IDKINOPOISK_EXTRA

//package com.example.filmslibrary.ui.details
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import com.example.filmslibrary.R
//import com.example.filmslibrary.databinding.DetailsFragmentBinding
//import com.example.filmslibrary.model.AppState
//import com.example.filmslibrary.model.entities.FilmCard
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//
//class DetailsFragment : Fragment() {
//    private var _binding: DetailsFragmentBinding? = null
//    private val binding get() = _binding!!
//    private val viewModel: DetailsViewModel by viewModel()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//
//    ): View {
//        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        arguments?.getParcelable<FilmCard>(BUNDLE_EXTRA)?.let {
//            with(binding) {
//                val film = it.film
//
//                movieLengthTitle.text = getString(R.string.movie_length)
//                filmYearTitle.text = getString(R.string.year)
//                ratingIMDbTitle.text = getString(R.string.rating)
//                filmName.text = film.filmName
//                movieLength.text = it.film.idKinopoisk.toString()
//
//                viewModel.filmLiveData.observe(viewLifecycleOwner, { appState ->
//                    when (appState) {
//                        is AppState.Error -> {
//                            mainView.visibility = View.INVISIBLE
//                            progressBar.visibility = View.GONE
//                            error.visibility = View.VISIBLE
//                        }
//                        AppState.Loading -> {
//                            mainView.visibility = View.INVISIBLE
//                            progressBar.visibility = View.VISIBLE
//                        }
//                        is AppState.Success -> {
//                            progressBar.visibility = View.GONE
//                            mainView.visibility = View.VISIBLE
//                            filmYear.text = appState.filmData[0].year.toString()
//                            ratingIMDb.text = appState.filmData[0].ratingIMDb.toString()
//                            description.text = appState.filmData[0].description
//                        }
//                    }
//                })
//            }
//            viewModel.loadData(it.film.idKinopoisk)
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    companion object {
//        const val BUNDLE_EXTRA = "film"
//
//        fun newInstance(bundle: Bundle): DetailsFragment {
//            val fragment = DetailsFragment()
//            fragment.arguments = bundle
//            return fragment
//        }
//    }
//}

const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val DETAILS_INTENT_EMPTY_EXTRA = "INTENT IS EMPTY"
const val DETAILS_DATA_EMPTY_EXTRA = "DATA IS EMPTY"
const val DETAILS_RESPONSE_EMPTY_EXTRA = "RESPONSE IS EMPTY"
const val DETAILS_REQUEST_ERROR_EXTRA = "REQUEST ERROR"
const val DETAILS_REQUEST_ERROR_MESSAGE_EXTRA = "REQUEST ERROR MESSAGE"
const val DETAILS_URL_MALFORMED_EXTRA = "URL MALFORMED"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"
const val DETAILS_TITLE_EXTRA = "TITLE"
const val DETAILS_YEAR_EXTRA = "YEAR"
const val DETAILS_RAITING_EXTRA = "RAITING"
const val DETAILS_DESCRIPTION_EXTRA = "DESCRIPTION"

private const val PROCESS_ERROR = "Обработка ошибки"

class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var filmBundle: FilmCard
    private val loadResultsReceiver: BroadcastReceiver = object :
        BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                DETAILS_INTENT_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_DATA_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_REQUEST_ERROR_MESSAGE_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_URL_MALFORMED_EXTRA -> TODO(PROCESS_ERROR)
                DETAILS_RESPONSE_SUCCESS_EXTRA -> renderData(
                        FilmDTO(
                            intent.getStringExtra(DETAILS_TITLE_EXTRA),
                            intent.getIntExtra(DETAILS_YEAR_EXTRA, 0),
                            intent.getDoubleExtra(DETAILS_RAITING_EXTRA, 0.0),
                            intent.getStringExtra(DETAILS_DESCRIPTION_EXTRA)
                        )
                    )

                else -> TODO(PROCESS_ERROR)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(loadResultsReceiver,
                    IntentFilter(DETAILS_INTENT_FILTER)
                )
        }
    }
    override fun onDestroy() {
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultsReceiver)
        }
        super.onDestroy()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: FilmCard(getDefaultFilm(),0,0.0, "No imformation")
        getFilms()
    }
    private fun getFilms() {
        binding.mainView.visibility = View.GONE
//        binding.loadingLayout.visibility = View.VISIBLE
        context?.let {
            it.startService(Intent(it, DetailsService::class.java).apply {
                putExtra(
                    FILENAME_EXTRA,
                    filmBundle.film.filmName
                )
                putExtra(
                    IDKINOPOISK_EXTRA,
                    filmBundle.film.idKinopoisk
                )
            })
        }
    }
    private fun renderData(filmDTO: FilmDTO) {
        binding.mainView.visibility = View.VISIBLE
//        binding.loadingLayout.visibility = View.GONE
        val fact = filmDTO
        val year = fact!!.year
        val raiting = fact.rating_imdb
        val descrip = fact.description
        if (year == null || raiting == null || descrip
            == null) {
            TODO(PROCESS_ERROR)
        } else {
            val film = filmBundle.film
            binding.filmName.text = film.filmName
//            binding.cityCoordinates.text = String.format(
//                getString(R.string.city_coordinates),
//                city.lat.toString(),
//                city.lon.toString()
//            )
            binding.filmYear.text = year.toString()
            binding.ratingIMDb.text = raiting.toString()
            binding.description.text = descrip
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
