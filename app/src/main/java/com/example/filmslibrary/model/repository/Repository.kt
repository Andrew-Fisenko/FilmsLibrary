package com.example.filmslibrary.model.repository

import com.example.filmslibrary.model.entities.Film
import com.example.filmslibrary.model.entities.FilmCard

interface Repository {
    fun getFilmFromServer(id_kinopoisk: Int): FilmCard
    fun getFilmFromLocalStorage(): List<FilmCard>
}