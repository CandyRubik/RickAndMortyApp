package ru.rubik.rickandmorty

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.rubik.rickandmorty.di.MainModules

class App: Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@App)
			modules(MainModules)
		}
	}
}