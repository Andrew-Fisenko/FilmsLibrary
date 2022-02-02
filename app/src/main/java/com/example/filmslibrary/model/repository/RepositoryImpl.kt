package com.example.filmslibrary.model.repository

import com.example.filmslibrary.model.entities.FilmCard
import com.example.filmslibrary.model.entities.getFilms

class RepositoryImpl : Repository {

    override fun getFilmFromServer() = getFilms()

    override fun getFilmFromLocalStorage() = getFilms()
}