package ru.rubik.rickandmorty.data.dtos

import com.google.gson.annotations.SerializedName

data  class LocationDTO(
    @SerializedName("name")
    val name: String,
)