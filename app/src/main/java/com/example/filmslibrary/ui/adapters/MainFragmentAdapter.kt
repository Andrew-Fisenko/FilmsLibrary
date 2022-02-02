package com.example.filmslibrary.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmslibrary.databinding.FragmentMainRecyclerItemBinding
import com.example.filmslibrary.model.entities.FilmCard
import com.example.filmslibrary.ui.main.MainFragment


class MainFragmentAdapter(private val itemClickListener: MainFragment.OnItemViewClickListener)
    : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var weatherData: List<FilmCard> = listOf()
    private lateinit var binding: FragmentMainRecyclerItemBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setFilm(data: List<FilmCard>) {
        weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = FragmentMainRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount() = weatherData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(filmCard: FilmCard) = with(binding) {
            mainFragmentRecyclerItemTextView.text = filmCard.film.filmName
            root.setOnClickListener { itemClickListener.onItemViewClick(filmCard) }
        }
    }
}