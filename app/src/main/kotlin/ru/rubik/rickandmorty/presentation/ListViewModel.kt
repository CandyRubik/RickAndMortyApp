package ru.rubik.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.rickandmorty.domain.usecases.GetHeroesInfoUseCase
import ru.rubik.rickandmorty.ui.list.state.ContentState
import ru.rubik.rickandmorty.ui.list.state.UiState
import ru.rubik.rickandmorty.utils.coroutineNetworkExceptionHandler

class ListViewModel(
    private val getHeroesInfoUseCase: GetHeroesInfoUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    private val errorHandler = coroutineNetworkExceptionHandler { code ->
        _uiState.value = _uiState.value.copy(contentState = ContentState.LoadingError(code))
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(errorHandler) {
            _uiState.value = _uiState.value.copy(contentState = ContentState.Loading)
            val heroes = getHeroesInfoUseCase()
            _uiState.value = _uiState.value.copy(
                content = heroes,
                contentState = ContentState.LoadingSuccessful,
            )
        }
    }
}