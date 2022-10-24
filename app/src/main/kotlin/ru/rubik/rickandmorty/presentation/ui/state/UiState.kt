package ru.rubik.rickandmorty.presentation.ui.state

import ru.rubik.rickandmorty.domain.entities.Hero

sealed class UiState {
	object Init: UiState()
	object Loading: UiState()
	data class Content(val content: List<Hero>): UiState()
	data class Error(val code: Int): UiState()
}
