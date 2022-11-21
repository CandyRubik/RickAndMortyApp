package ru.rubik.rickandmorty.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Fade
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.rubik.rickandmorty.R
import ru.rubik.rickandmorty.databinding.FragmentListBinding
import ru.rubik.rickandmorty.presentation.ListViewModel
import ru.rubik.rickandmorty.ui.DetailFragment
import ru.rubik.rickandmorty.ui.error_dialog.ErrorDialog
import ru.rubik.rickandmorty.ui.list.adapter.HeroesAdapter
import ru.rubik.rickandmorty.ui.list.state.ContentState
import ru.rubik.rickandmorty.ui.list.state.UiState

class ListFragment : Fragment(R.layout.fragment_list) {

    private val binding by viewBinding(FragmentListBinding::bind)

    private val adapter by lazy {
        HeroesAdapter(
            onItemClickListener = { hero ->
                parentFragmentManager.commit {
                    replace(R.id.fragmentContainer, DetailFragment.newInstance(hero))
                    addToBackStack(null)
                }
            }
        )
    }

    private val viewModel by viewModel<ListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = Fade()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                obtainContentState(it)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.activity)
        binding.recyclerView.adapter = adapter
    }

    private fun obtainContentState(uiState: UiState) {
        when (uiState.contentState) {
            ContentState.Loading -> {
                binding.progressBar.isVisible = true
                binding.recyclerView.isVisible = false
            }
            is ContentState.LoadingError -> {
                ErrorDialog().show(
                    parentFragmentManager,
                    "tag",
                )
            }
            ContentState.LoadingSuccessful -> {
                adapter.heroes = uiState.content
                binding.progressBar.isVisible = false
                binding.recyclerView.isVisible = true
            }
        }
    }
}