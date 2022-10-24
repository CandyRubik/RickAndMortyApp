package ru.rubik.rickandmorty.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.rubik.rickandmorty.R
import ru.rubik.rickandmorty.databinding.ItemHeroBinding
import ru.rubik.rickandmorty.domain.entities.Hero

class HeroesAdapter : RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

	var heroes: List<Hero> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = ItemHeroBinding.inflate(inflater, parent, false)
		return HeroViewHolder(binding)
	}

	override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
		val hero = heroes[position]
		with(holder.binding) {
			name.text = hero.name
			status.text = hero.status
			species.text = hero.species
			Glide.with(image.context)
				.load(hero.image)
				.circleCrop()
				.placeholder(R.drawable.ic_baseline_account)
				.into(image)
		}
	}

	override fun getItemCount(): Int = heroes.size

	class HeroViewHolder(
		val binding: ItemHeroBinding
	) : RecyclerView.ViewHolder(binding.root)

}