package com.example.filmslibrary.model.entities.rest_entities

import com.google.gson.annotations.SerializedName

data class FilmDTO(
    @SerializedName("title")
    val title: String?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("rating_imdb")
    val rating_imdb: Double?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("poster")
    val poster: String?,
)


