package com.example.filmslibrary.model.repository

import com.example.filmslibrary.model.FilmLoader
import com.example.filmslibrary.model.entities.Film
import com.example.filmslibrary.model.entities.FilmCard
import com.example.filmslibrary.model.entities.getFilms

class RepositoryImpl : Repository {

    override fun getFilmFromServer(id_kinopoisk: Int): FilmCard {
        val dto = FilmLoader.loadFilm(id_kinopoisk)
        return FilmCard(
            year = dto?.year ?: 0,
            ratingIMDb = dto?.rating_imdb ?: 0.0,
            description = dto?.description ?: "No information"
        )
    }

    override fun getFilmFromLocalStorage() = getFilms()
}