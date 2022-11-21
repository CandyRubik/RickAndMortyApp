package ru.rubik.rickandmorty.ui.greeting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.rickandmorty.R
import ru.rubik.rickandmorty.databinding.FragmentGreetingBinding
import ru.rubik.rickandmorty.ui.list.ListFragment

class GreetingFragment: Fragment(R.layout.fragment_greeting) {
    private val binding by viewBinding(FragmentGreetingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadButton.setOnClickListener {
            parentFragmentManager.commit {
                replace<ListFragment>(R.id.fragmentContainer)
            }
        }
    }
}