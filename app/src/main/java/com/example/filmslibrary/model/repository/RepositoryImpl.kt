package com.example.filmslibrary.model.repository

import com.example.filmslibrary.model.FilmLoader
import com.example.filmslibrary.model.entities.Film
import com.example.filmslibrary.model.entities.FilmCard
import com.example.filmslibrary.model.entities.getDefaultFilm
import com.example.filmslibrary.model.entities.getFilms
import com.example.filmslibrary.model.entities.rest_entities.FilmDTO
import com.example.filmslibrary.model.entities.rest_entities.FilmRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl : Repository {

    override fun getFilmFromServer(id_kinopoisk: Int): FilmCard {
//        val dto = FilmLoader.loadFilm(id_kinopoisk)
        val dto = FilmRepo.api.getFilm(id_kinopoisk).execute().body()
//        val dto = FilmRepo.api.getFilm(id_kinopoisk).enqueue(object : Callback<FilmDTO> {
//            override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>){
//                if (response.isSuccessful){
//                    val data = response.body()
//                }
//            }
//            override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
        return FilmCard(
            year = dto?.year ?: 0,
            ratingIMDb = dto?.rating_imdb ?: 0.0,
            description = dto?.description ?: "No information",
            poster = dto?.poster ?: "https://freepngimg.com/thumb/city/36275-3-city-hd.png"
        )
        return FilmCard(getDefaultFilm(),0,0.0, "No", "https://freepngimg.com/thumb/city/36275-3-city-hd.png")
    }

    override fun getFilmFromLocalStorage() = getFilms()
}