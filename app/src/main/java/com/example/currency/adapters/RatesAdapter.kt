package com.example.currency.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.data.models.Rate
import com.example.currency.databinding.ItemCurrencyBinding

class RatesAdapter : ListAdapter<Rate, RatesAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(rate: Rate) {
            binding.apply {
                tvPair.text = rate.pair
                tvValue.text = rate.rate.toString()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Rate>() {
        override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem.pair == newItem.pair
        }
        override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rate = getItem(position)
        holder.bind(rate)
    }
}