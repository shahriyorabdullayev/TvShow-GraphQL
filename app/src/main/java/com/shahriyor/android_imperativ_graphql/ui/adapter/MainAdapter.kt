package com.shahriyor.android_imperativ_graphql.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahriyor.android_graphql.PopularMoviesQuery.*
import com.shahriyor.android_imperativ_graphql.databinding.ItemTvShowBinding
import com.shahriyor.android_imperativ_graphql.utils.Constants.BASE_IMAGE_URL
import com.shahriyor.android_imperative.utils.RandomColor


class MainAdapter() :
    ListAdapter<Movie, MainAdapter.MainViewHolder>(Comparator()) {


    inner class MainViewHolder(private val binding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun bind(item: Movie?) {
            Glide.with(binding.ivMovie).load(BASE_IMAGE_URL + item?.poster_path)
                .placeholder(RandomColor.randomColor()).into(binding.ivMovie)
            binding.tvName.text = item?.original_title
        }
    }

    class Comparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie,
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemTvShowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


}