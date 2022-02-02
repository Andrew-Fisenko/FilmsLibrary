package com.example.filmslibrary.ui.main

import com.example.filmslibrary.model.repository.Repository
import com.example.filmslibrary.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }
}

