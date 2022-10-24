package ru.rubik.rickandmorty.data.dtos

import com.google.gson.annotations.SerializedName

data class HeroesDTO (
	@SerializedName("results")
	val result: List<HeroDTO>
)