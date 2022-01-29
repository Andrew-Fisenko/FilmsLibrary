package com.example.filmslibrary.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmslibrary.model.AppState
import com.example.filmslibrary.model.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    private val localLiveData: MutableLiveData<AppState> = MutableLiveData()
    val filmLiveData: LiveData<AppState>
        get() {
            return localLiveData
        }

    fun loadData(idKinopoisk: Int) {
        localLiveData.value = AppState.Loading
        Thread {
            val data = repository.getFilmFromServer(idKinopoisk)
            localLiveData.postValue(AppState.Success(listOf(data)))
        }.start()
    }

}