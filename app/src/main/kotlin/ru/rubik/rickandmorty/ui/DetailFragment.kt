package ru.rubik.rickandmorty.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.rubik.rickandmorty.R
import ru.rubik.rickandmorty.databinding.FragmentDetailBinding
import ru.rubik.rickandmorty.domain.entities.Hero

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hero = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(HERO_KEY, Hero::class.java)
        } else {
            requireArguments().getParcelable(HERO_KEY)
        }

        with(binding) {
            name.text = hero?.name
            status.text = getString(R.string.item_character_status_template, hero?.status)
            species.text = getString(R.string.item_character_species_template, hero?.species)
            location.text = getString(R.string.item_character_location_template, hero?.location)
            Glide.with(image.context)
                .load(hero?.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .circleCrop()
                .into(image)
        }
    }

    companion object {

        private const val HERO_KEY = "HERO_KEY"

        fun newInstance(hero: Hero): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(HERO_KEY, hero)
                }
            }
        }
    }
}