package ru.rubik.rickandmorty.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
	val id: String,
	val name: String,
	val status: String,
	val species: String,
	val image: String,
	val location: String,
): Parcelable
