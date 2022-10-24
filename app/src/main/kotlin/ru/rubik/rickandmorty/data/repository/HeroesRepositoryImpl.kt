package ru.rubik.rickandmorty.data.repository

import com.bumptech.glide.Glide
import ru.rubik.rickandmorty.data.datasource.HeroesDataSource
import ru.rubik.rickandmorty.data.dtos.HeroDTO
import ru.rubik.rickandmorty.domain.entities.Hero
import ru.rubik.rickandmorty.domain.repository.HeroesRepository

fun mapHeroToDomain(item: HeroDTO): Hero {
	return Hero(
		name = item.name,
		status = item.status,
		species = item.species,
		image = item.image,
	)
}

fun List<HeroDTO>.mapToDomain(): List<Hero> {
	return this.map(::mapHeroToDomain)
}

class HeroesRepositoryImpl(
	private val dataSource: HeroesDataSource
): HeroesRepository {

	override suspend fun getCharacters(): List<Hero> {
		return dataSource.getHeroes().mapToDomain()
	}
}