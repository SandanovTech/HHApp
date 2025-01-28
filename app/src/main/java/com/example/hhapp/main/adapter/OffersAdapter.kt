package com.example.hhapp.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hhapp.databinding.OffersBinding
import com.example.hhapp.main.model.ListOffersDTO

class OffersAdapter(
    private val offersDTO: ListOffersDTO,
) : RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {
    inner class OffersViewHolder(binding: OffersBinding) : RecyclerView.ViewHolder(binding.root) {
        var icon = binding.icon
        var title = binding.titleCardView
        var pickUp = binding.pickUp.apply {
            this.setTextColor(Color.GREEN)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        val binding = OffersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OffersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return offersDTO.offers.size
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        val offer = offersDTO.offers[position]
        holder.title.text = offer.title
        holder.pickUp.text = offer.button?.text
    }
}