package ru.rubik.rickandmorty.data.datasource

import ru.rubik.rickandmorty.data.dtos.HeroDTO
import ru.rubik.rickandmorty.network.RickAndMortyService

class HeroesDataSourceImpl(
	private val rickAndMortyService: RickAndMortyService
): HeroesDataSource {

	override suspend fun getHeroes(): List<HeroDTO> {
		return rickAndMortyService.getCharacters().result
	}
}