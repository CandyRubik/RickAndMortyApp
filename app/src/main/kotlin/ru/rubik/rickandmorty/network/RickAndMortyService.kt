package ru.rubik.rickandmorty.network

import retrofit2.http.GET
import ru.rubik.rickandmorty.data.dtos.HeroesDTO

interface RickAndMortyService {
	@GET("character")
	suspend fun getCharacters(): HeroesDTO
}