package ru.rubik.rickandmorty.data.dtos

import com.google.gson.annotations.SerializedName

data class HeroDTO(
	@SerializedName("id")
	val id: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("status")
	val status: String,
	@SerializedName("species")
	val species: String,
	@SerializedName("image")
	val image: String,
	@SerializedName("location")
	val location: LocationDTO,
)
