package com.example.filmslibrary.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmslibrary.model.AppState
import com.example.filmslibrary.model.repository.Repository
import java.lang.Thread.sleep


class MainViewModel(private val repository: Repository) : ViewModel() {

    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getFilm() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveData.value = AppState.Loading
        Thread {
            sleep(1000)
            liveData.postValue(AppState.Success(repository.getFilmFromLocalStorage()))
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
    }
}