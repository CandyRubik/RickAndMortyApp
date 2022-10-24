package ru.rubik.rickandmorty.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.rubik.rickandmorty.domain.repository.HeroesRepository

class GetHeroesInfoUseCase(private val repository: HeroesRepository) {
	suspend operator fun invoke() = withContext(Dispatchers.IO) {
		repository.getCharacters()
	}
}