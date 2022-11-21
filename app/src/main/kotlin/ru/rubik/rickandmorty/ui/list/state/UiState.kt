package ru.rubik.rickandmorty.ui.list.state

import ru.rubik.rickandmorty.domain.entities.Hero

data class UiState(
    val content: List<Hero> = emptyList(),
    val contentState: ContentState = ContentState.Loading,
)

sealed interface ContentState {
    object Loading : ContentState
    object LoadingSuccessful : ContentState
    data class LoadingError(val code: Int) : ContentState
}
