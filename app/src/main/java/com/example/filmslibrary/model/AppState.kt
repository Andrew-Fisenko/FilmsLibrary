package com.example.filmslibrary.model

import com.example.filmslibrary.model.entities.FilmCard

sealed class AppState {
    data class Success(val filmData: List<FilmCard>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

