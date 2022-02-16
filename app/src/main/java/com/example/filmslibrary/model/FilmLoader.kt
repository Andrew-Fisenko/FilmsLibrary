package com.example.filmslibrary.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.filmslibrary.model.entities.rest_entities.FilmDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object FilmLoader {
//    fun loadFilm(idKinopoisk: Int): FilmDTO? {
//        try {
//            val uri =
//                URL("https://cloud-api.kinopoisk.dev/movies/${idKinopoisk}/token/ae3250a07bde56abbe919ed8b9babbaf")
//
//            lateinit var urlConnection: HttpsURLConnection
//            try {
//                urlConnection = uri.openConnection() as HttpsURLConnection
//                urlConnection.requestMethod = "GET"
////                urlConnection.addRequestProperty(
////                    "Key", "Value"
////                )
//                urlConnection.readTimeout = 10000
//                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
//                val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//                    getLinesForOld(bufferedReader)
//                } else {
//                    getLines(bufferedReader)
//                }
//
//                return Gson().fromJson(lines, FilmDTO::class.java)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            } finally {
//                urlConnection.disconnect()
//            }
//        } catch (e: MalformedURLException) {
//            e.printStackTrace()
//        }
//        return null
//    }

    private fun getLinesForOld(reader: BufferedReader): String {
        val rawData = StringBuilder(1024)
        var tempVariable: String?

        while (reader.readLine().also { tempVariable = it } != null) {
            rawData.append(tempVariable).append("\n")
        }
        reader.close()
        return rawData.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}
