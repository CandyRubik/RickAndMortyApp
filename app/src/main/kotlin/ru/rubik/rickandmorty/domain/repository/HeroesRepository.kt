package ru.rubik.rickandmorty.domain.repository

import ru.rubik.rickandmorty.domain.entities.Hero

interface HeroesRepository {
	suspend fun getCharacters(): List<Hero>
}