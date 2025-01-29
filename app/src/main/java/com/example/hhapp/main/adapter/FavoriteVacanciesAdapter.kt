package com.example.hhapp.main.adapter

import android.icu.text.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hhapp.R
import com.example.hhapp.databinding.VacanciesBinding
import com.example.hhapp.main.model.ListVacanciesDTO
import com.example.hhapp.main.model.VacanciesDTO
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FavoriteVacanciesAdapter(
    vacanciesDTO: ListVacanciesDTO,
) : RecyclerView.Adapter<FavoriteVacanciesAdapter.VacanciesViewHolder>() {

    //    private val favoriteVacancies = vacanciesDTO.vacancies.filter { it.isFavorite }
    private val favoriteVacancies = mutableListOf<VacanciesDTO>()

    init {
        updateFavorites(vacanciesDTO)
    }

    inner class VacanciesViewHolder(binding: VacanciesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var lookingLabel = binding.lookingLabel
        var vacancy = binding.vacancy
        var price = binding.price
        var city = binding.city
        var experienceLabel = binding.experienceLabel
        var company = binding.company
        var publicationDate = binding.publicationDate
        var continueBtn = binding.continueBtn
        val favorite = binding.favorite
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        val binding = VacanciesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacanciesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favoriteVacancies.size
    }

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        val vacancy = favoriteVacancies[position]
        val lookingCounter = vacancy.lookingNumber
        if (lookingCounter != null) {
            holder.lookingLabel.text = holder.itemView.context.getString(
                R.string.currently_n_people_are_viewing,
                lookingCounter
            )
        } else {
            holder.lookingLabel.visibility = View.GONE
        }

        holder.vacancy.text = vacancy.title
        holder.company.text = vacancy.company
        setupDateFormat(vacancy, holder)
        vacancy.address.let { address ->
            holder.city.text = address?.town
        }
        holder.experienceLabel.text = vacancy.experience?.previewText
        val price = vacancy.salary?.short
        if (price != null) {
            holder.price.text = price
        } else {
            holder.price.visibility = View.GONE
        }
        val newIcon = if (vacancy.isFavorite) R.drawable.heart_active else R.drawable.heart
        holder.favorite.setImageResource(newIcon)
        holder.favorite.setOnClickListener {
            vacancy.isFavorite = !vacancy.isFavorite
            val newIconClicked =
                if (vacancy.isFavorite) R.drawable.heart_active else R.drawable.heart
            holder.favorite.setImageResource(newIconClicked)
        }
    }

    private fun setupDateFormat(
        vacancy: VacanciesDTO,
        holder: VacanciesViewHolder,
    ) {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputDateFormat = DateFormat.getPatternInstance(DateFormat.ABBR_MONTH_DAY)
        val date: Date = vacancy.publishedDate?.let { inputDateFormat.parse(it) } as Date
        holder.publicationDate.text = holder.itemView.context.getString(
            R.string.publication_date,
            outputDateFormat.format(date)
        )
    }

    fun updateFavorites(vacanciesDTO: ListVacanciesDTO) {
        favoriteVacancies.clear()
        favoriteVacancies.addAll(vacanciesDTO.vacancies.filter { it.isFavorite })
        notifyDataSetChanged()
    }
}