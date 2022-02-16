package com.example.filmslibrary.ui.details


import DETAILS_DATA_EMPTY_EXTRA
import DETAILS_DESCRIPTION_EXTRA

import DETAILS_INTENT_EMPTY_EXTRA
import DETAILS_INTENT_FILTER
import DETAILS_LOAD_RESULT_EXTRA
import DETAILS_RAITING_EXTRA
import DETAILS_REQUEST_ERROR_EXTRA
import DETAILS_REQUEST_ERROR_MESSAGE_EXTRA
import DETAILS_RESPONSE_EMPTY_EXTRA
import DETAILS_RESPONSE_SUCCESS_EXTRA

import DETAILS_URL_MALFORMED_EXTRA
import DETAILS_YEAR_EXTRA
import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.filmslibrary.BuildConfig
import com.example.filmslibrary.model.entities.rest_entities.FilmDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

const val FILENAME_EXTRA = "FilmName"
const val IDKINOPOISK_EXTRA = "IDKinopoisk"
private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10000
private const val REQUEST_API_KEY = "X-Yandex-API-Key"

class DetailsService(name: String = "DetailService") : IntentService(name) {
    private val broadcastIntent = Intent(DETAILS_INTENT_FILTER)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) {
            onEmptyIntent()
        } else {
//            val lat = intent.getDoubleExtra(FILENAME_EXTRA, 0.0)
            val idKinopoisk = intent.getIntExtra(IDKINOPOISK_EXTRA, 0)
            if (idKinopoisk == 0) {
                onEmptyData()
            } else {
                loadFilm(idKinopoisk.toString())
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadFilm(idKinopoisk: String){
        try {
            val uri =
                URL("https://cloud-api.kinopoisk.dev/movies/${idKinopoisk}/token/ae3250a07bde56abbe919ed8b9babbaf")

            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
//                urlConnection = uri.openConnection() as HttpsURLConnection
//                urlConnection.apply {
//                    requestMethod = REQUEST_GET
//                    readTimeout = REQUEST_TIMEOUT
////                    addRequestProperty(REQUEST_API_KEY,
////                        BuildConfig.WEATHER_API_KEY
////                    )
//                }

//                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
//                val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//                    getLinesForOld(bufferedReader)
//                } else {
//                    getLines(bufferedReader)
//                }
//
//                val filmDTO: FilmDTO = Gson().fromJson(lines, FilmDTO::class.java)


                val filmDTO: FilmDTO =
                    Gson().fromJson(
                        getLines(BufferedReader(InputStreamReader(urlConnection.inputStream))),
                        FilmDTO::class.java
                    )
                onResponse(filmDTO)
            } catch (e: Exception) {
                onErrorRequest(e.message ?: "Empty error")
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            onMalformedURL()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
    private fun onResponse(filmDTO: FilmDTO) {
        val fact = filmDTO
        if (fact == null) {
            onEmptyResponse()
        } else {
            onSuccessResponse(fact.year, fact.rating_imdb, fact.description)
        }
    }
    private fun onSuccessResponse(year: Int?, rating_imdb: Double?, description:
    String?) {
        putLoadResult(DETAILS_RESPONSE_SUCCESS_EXTRA)
        broadcastIntent.putExtra(DETAILS_YEAR_EXTRA, year)
        broadcastIntent.putExtra(DETAILS_RAITING_EXTRA, rating_imdb)
        broadcastIntent.putExtra(DETAILS_DESCRIPTION_EXTRA, description)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
    private fun onMalformedURL() {
        putLoadResult(DETAILS_URL_MALFORMED_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
    private fun onErrorRequest(error: String) {
        putLoadResult(DETAILS_REQUEST_ERROR_EXTRA)
        broadcastIntent.putExtra(DETAILS_REQUEST_ERROR_MESSAGE_EXTRA, error)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
    private fun onEmptyResponse() {
        putLoadResult(DETAILS_RESPONSE_EMPTY_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
    private fun onEmptyIntent() {
        putLoadResult(DETAILS_INTENT_EMPTY_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
    private fun onEmptyData() {
        putLoadResult(DETAILS_DATA_EMPTY_EXTRA)
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
    private fun putLoadResult(result: String) {
        broadcastIntent.putExtra(DETAILS_LOAD_RESULT_EXTRA, result)
    }

//    private fun getLinesForOld(reader: BufferedReader): String {
//        val rawData = StringBuilder(1024)
//        var tempVariable: String?
//
//        while (reader.readLine().also { tempVariable = it } != null) {
//            rawData.append(tempVariable).append("\n")
//        }
//        reader.close()
//        return rawData.toString()
//    }
}
