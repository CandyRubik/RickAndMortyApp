package ru.rubik.rickandmorty.ui.error_dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.rubik.rickandmorty.R
import ru.rubik.rickandmorty.databinding.FragmentErrorBinding
import ru.rubik.rickandmorty.ui.list.ListFragment

class ErrorDialog : DialogFragment() {

    private val binding: FragmentErrorBinding by lazy {
        FragmentErrorBinding.inflate(layoutInflater)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding.repeatButton.setOnClickListener {
            parentFragmentManager.commit {
                replace<ListFragment>(R.id.fragmentContainer)
            }
            dismiss()
        }

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
            .also {
                it.window?.setBackgroundDrawable(
                    ColorDrawable(Color.TRANSPARENT)
                )
            }
    }
}