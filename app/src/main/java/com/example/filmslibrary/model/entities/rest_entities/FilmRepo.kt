package com.example.filmslibrary.model.entities.rest_entities


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FilmRepo {
    val api: FilmAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilderWithHeaders())
            .build()

        adapter.create(FilmAPI::class.java)
    }
}