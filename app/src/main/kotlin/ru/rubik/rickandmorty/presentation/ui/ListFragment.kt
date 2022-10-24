package ru.rubik.rickandmorty.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.rubik.rickandmorty.databinding.FragmentListBinding
import ru.rubik.rickandmorty.presentation.ListViewModel
import ru.rubik.rickandmorty.presentation.ui.adapter.HeroesAdapter
import ru.rubik.rickandmorty.presentation.ui.state.UiState

class ListFragment : Fragment() {

	private var _binding: FragmentListBinding? = null
	private val binding
		get() = _binding!!

	private var adapter: HeroesAdapter? = null

	private val viewModel by viewModel<ListViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentListBinding.inflate(inflater)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bind(viewModel)

		viewLifecycleOwner.lifecycleScope.launchWhenStarted {
			viewModel.uiState.collect {
				renderUiState(it)
			}
		}

		adapter = HeroesAdapter()

		binding.recyclerView.layoutManager = LinearLayoutManager(this.activity)
		binding.recyclerView.adapter = adapter
	}

	private fun renderUiState(uiState: UiState) {
		when (uiState) {
			is UiState.Init    -> {
				binding.loadButton.visibility = View.VISIBLE
				binding.errorScreen.visibility = View.INVISIBLE
				binding.recyclerView.visibility = View.INVISIBLE
				binding.progressCyclic.visibility = View.INVISIBLE
			}

			is UiState.Content -> {
				adapter!!.heroes = uiState.content
				binding.loadButton.visibility = View.INVISIBLE
				binding.errorScreen.visibility = View.INVISIBLE
				binding.recyclerView.visibility = View.VISIBLE
				binding.progressCyclic.visibility = View.INVISIBLE
			}

			is UiState.Error   -> {
				binding.loadButton.visibility = View.INVISIBLE
				binding.errorScreen.visibility = View.VISIBLE
				binding.recyclerView.visibility = View.INVISIBLE
				binding.progressCyclic.visibility = View.INVISIBLE
			}

			is UiState.Loading -> {
				binding.loadButton.visibility = View.INVISIBLE
				binding.errorScreen.visibility = View.INVISIBLE
				binding.recyclerView.visibility = View.INVISIBLE
				binding.progressCyclic.visibility = View.VISIBLE
			}
		}
	}

	private fun bind(viewModel: ListViewModel) {
		with(binding) {

			loadButton.setOnClickListener {
				viewModel.onLoadData()
			}
			repeatButton.setOnClickListener {
				viewModel.onLoadData()
			}
		}
	}
}