package com.example.hhapp.main.adapter

import android.icu.text.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hhapp.R
import com.example.hhapp.databinding.VacanciesBinding
import com.example.hhapp.main.model.ListVacanciesDTO
import com.example.hhapp.main.model.VacanciesDTO
import com.example.hhapp.main.ui.MainFragmentDirections
import com.example.hhapp.main.ui.MoreVacanciesFragmentDirections
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class VacanciesAdapter(
    val vacanciesDTO: ListVacanciesDTO,
) : ListAdapter<VacanciesDTO,VacanciesAdapter.VacanciesViewHolder>(DiffCallback()) {

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
        val cardVacancy = binding.cardVacancy
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        val binding = VacanciesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacanciesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return vacanciesDTO.vacancies.size
    }

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        val vacancy = vacanciesDTO.vacancies[position]
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
        holder.favorite.setImageResource(
            if (vacancy.isFavorite) R.drawable.heart_active else R.drawable.heart
        )
        holder.favorite.setOnClickListener {
            vacancy.isFavorite = !vacancy.isFavorite
            val newIconClicked =
                if (vacancy.isFavorite) R.drawable.heart_active else R.drawable.heart
            holder.favorite.setImageResource(newIconClicked)
        }
        holder.cardVacancy.setOnClickListener {
            val navController = it.findNavController()
            val currentDestination = navController.currentDestination?.id
            when (currentDestination) {
                R.id.mainFragment -> {
                    val action =
                        MainFragmentDirections.actionMainFragmentToVacancyDetailsFragment(vacancy.id.toString())
                    navController.navigate(action)
                }
                R.id.moreVacanciesFragment -> {
                    val action =
                        MoreVacanciesFragmentDirections.actionMoreVacanciesFragmentToVacancyDetailsFragment(vacancy.id.toString())
                    navController.navigate(action)
                }
            }
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
}

class DiffCallback : DiffUtil.ItemCallback<VacanciesDTO>() {
    override fun areItemsTheSame(oldItem: VacanciesDTO, newItem: VacanciesDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VacanciesDTO, newItem: VacanciesDTO): Boolean {
        return oldItem.id == newItem.id
    }

}
