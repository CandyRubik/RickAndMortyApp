package ru.rubik.rickandmorty.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://rickandmortyapi.com/api/"

fun networkService(interceptors: List<Interceptor>): RickAndMortyService {
	val httpClient = OkHttpClient().newBuilder().apply {
		interceptors.forEach(::addInterceptor)
	}.build()

	return Retrofit.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.client(httpClient)
		.build().create(RickAndMortyService::class.java)
}