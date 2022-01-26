package com.example.filmslibrary.model.entities

data class Film(
    val filmName: String,
    val year: Int,
    val movieLength: Int,
    val ratingIMDb: Double,
    val description: String,
)
