package ru.rubik.rickandmorty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.rubik.rickandmorty.R
import ru.rubik.rickandmorty.databinding.ActivityMainBinding
import ru.rubik.rickandmorty.ui.greeting.GreetingFragment
import ru.rubik.rickandmorty.ui.list.ListFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace<GreetingFragment>(R.id.fragmentContainer)
        }
    }
}