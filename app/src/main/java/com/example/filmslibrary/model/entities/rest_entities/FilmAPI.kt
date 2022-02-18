package com.example.filmslibrary.model.entities.rest_entities

import com.example.filmslibrary.model.entities.rest_entities.FilmDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmAPI {

    @GET("movies/{id_kinopoisk}/token/ae3250a07bde56abbe919ed8b9babbaf")
    fun getFilm(
        @Path("id_kinopoisk") id_kinopoisk: Int
    ): Call<FilmDTO>
}