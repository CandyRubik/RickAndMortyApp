package ru.rubik.rickandmorty.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.rubik.rickandmorty.R
import ru.rubik.rickandmorty.databinding.ItemHeroBinding
import ru.rubik.rickandmorty.domain.entities.Hero

class HeroesAdapter(
    private val onItemClickListener: (Hero) -> Unit,
) : RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

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
			root.setOnClickListener {
				onItemClickListener(hero)
			}
            name.text = hero.name
            status.text = hero.status
            species.text = hero.species
            Glide.with(image.context)
                .load(hero.image)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
        }
    }

    override fun getItemCount(): Int = heroes.size

    class HeroViewHolder(
        val binding: ItemHeroBinding
    ) : RecyclerView.ViewHolder(binding.root)

}