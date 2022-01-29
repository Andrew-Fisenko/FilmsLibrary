package com.example.filmslibrary.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val filmName: String,
    val idKinopoisk: Int,
) : Parcelable
