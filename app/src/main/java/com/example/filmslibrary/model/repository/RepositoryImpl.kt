package com.example.filmslibrary.model.repository

import com.example.filmslibrary.model.entities.FilmCard

class RepositoryImpl : Repository {

    override fun getFilmFromServer() = FilmCard()

    override fun getFilmFromLocalStorage() = FilmCard()
}