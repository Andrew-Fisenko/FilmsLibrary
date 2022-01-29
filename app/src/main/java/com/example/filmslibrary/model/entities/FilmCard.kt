package com.example.filmslibrary.model.entities

import android.os.Parcelable
import com.example.filmslibrary.model.entities.Film
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmCard(

    val film: Film = getDefaultFilm(),
    val year: Int,
    val ratingIMDb: Double,
    val description: String,

    ) : Parcelable

fun getDefaultFilm() = Film("Матрица", 301)

fun getFilms() = listOf(
    FilmCard(Film("Челюсти", 396), 1900, 0.0, " "),
    FilmCard(Film("Матрица", 301), 1900, 0.0, " "),
    FilmCard(Film("Джентельмены", 1143242), 1900, 0.0, " "),
    FilmCard(Film("Человек-паук ", 838), 1900, 0.0, " "),
    FilmCard(Film("Крик", 1807), 1900, 0.0, " "),
)
