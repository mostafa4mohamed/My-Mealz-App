package com.example.mealzapp.ui.mealz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entitey.Category
import com.example.mealzapp.databinding.ItemResultBinding
import javax.inject.Inject

class MealzAdapter @Inject constructor() :
    ListAdapter<Category, MealzAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Category) {

            binding.apply {
                tvTitle.text=data.strCategory
                tvBody.text=data.strCategoryDescription
            }

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(
            oldItem: Category, newItem: Category
        ): Boolean = newItem == oldItem

        override fun areContentsTheSame(
            oldItem: Category, newItem: Category
        ): Boolean = newItem == oldItem
    }
}