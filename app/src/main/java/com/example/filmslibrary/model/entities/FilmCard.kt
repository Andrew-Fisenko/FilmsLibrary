package com.example.filmslibrary.model.entities

import com.example.filmslibrary.model.entities.Film

data class FilmCard(
    val film: Film = getDefaultFilm(),

    )

fun getDefaultFilm() = Film ("Матрица", 1999, 136, 8.5,
    "Жизнь Томаса Андерсона разделена на две части: днём он — самый обычный офисный " +
            "работник,получающий нагоняи от начальства, а ночью превращается в хакера по имени Нео, " +
            "и нет места в сети, куда он бы не смог проникнуть. Но однажды всё меняется. Томас " +
            "узнаёт ужасающую правду о реальности.")


