package ru.rubik.rickandmorty.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.rubik.rickandmorty.data.datasource.HeroesDataSource
import ru.rubik.rickandmorty.data.datasource.HeroesDataSourceImpl
import ru.rubik.rickandmorty.data.repository.HeroesRepositoryImpl
import ru.rubik.rickandmorty.domain.repository.HeroesRepository
import ru.rubik.rickandmorty.domain.usecases.GetHeroesInfoUseCase
import ru.rubik.rickandmorty.network.interceptors.NetworkConnectionInterceptor
import ru.rubik.rickandmorty.network.interceptors.loggingInterceptor
import ru.rubik.rickandmorty.network.RickAndMortyService
import ru.rubik.rickandmorty.network.networkService
import ru.rubik.rickandmorty.presentation.ListViewModel

val ViewModel = module {
	viewModel {
		ListViewModel(
			getHeroesInfoUseCase = get(),
		)
	}
}

val DataModule = module {
	single<RickAndMortyService> {
		networkService(
			listOf(
				loggingInterceptor(),
				NetworkConnectionInterceptor(
					context = androidContext()
				)
			)
		)
	}
	single<HeroesDataSource> { HeroesDataSourceImpl(rickAndMortyService = get()) }
	single<HeroesRepository> { HeroesRepositoryImpl(dataSource = get()) }
}

val UseCaseModule = module {
	factory { GetHeroesInfoUseCase(repository = get()) }
}

val MainModules = listOf(
	DataModule,
	UseCaseModule,
	ViewModel,
)