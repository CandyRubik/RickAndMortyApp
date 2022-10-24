package ru.rubik.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.rickandmorty.domain.usecases.GetHeroesInfoUseCase
import ru.rubik.rickandmorty.presentation.ui.state.UiState
import ru.rubik.rickandmorty.utils.coroutineNetworkExceptionHandler

class ListViewModel(
	private val getHeroesInfoUseCase: GetHeroesInfoUseCase,
): ViewModel() {
	private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Init)
	val uiState: StateFlow<UiState> = _uiState

	private val errorHandler = coroutineNetworkExceptionHandler { code ->
		_uiState.value = UiState.Error(code)
	}

	fun onLoadData() {
		viewModelScope.launch(errorHandler) {
			_uiState.value = UiState.Loading
			val heroes = getHeroesInfoUseCase()
			_uiState.value = UiState.Content(heroes)
		}
	}
}