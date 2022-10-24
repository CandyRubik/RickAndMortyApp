package ru.rubik.rickandmorty.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.rubik.rickandmorty.R
import ru.rubik.rickandmorty.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val fragment = ListFragment()

		supportFragmentManager
			.beginTransaction()
			.add(R.id.fragmentContainer, fragment)
			.commit()
	}
}