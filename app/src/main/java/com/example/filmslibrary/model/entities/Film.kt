package com.example.filmslibrary.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val filmName: String,
    val year: Int,
    val movieLength: Int,
    val ratingIMDb: Double,
    val description: String,
) : Parcelable
