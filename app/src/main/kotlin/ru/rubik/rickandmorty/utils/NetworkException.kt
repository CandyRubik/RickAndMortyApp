package ru.rubik.rickandmorty.utils

import java.io.IOException

class NetworkException(
	val code: Int, message: String?
) : IOException(message)