package com.example.filmslibrary.model.repository

import com.example.filmslibrary.model.entities.FilmCard

interface Repository {
    fun getFilmFromServer(): FilmCard
    fun getFilmFromLocalStorage(): FilmCard
}