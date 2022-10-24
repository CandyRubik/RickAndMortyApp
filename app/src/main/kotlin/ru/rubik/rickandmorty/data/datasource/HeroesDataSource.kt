package ru.rubik.rickandmorty.data.datasource

import ru.rubik.rickandmorty.data.dtos.HeroDTO

interface HeroesDataSource {
	suspend fun getHeroes(): List<HeroDTO>
}