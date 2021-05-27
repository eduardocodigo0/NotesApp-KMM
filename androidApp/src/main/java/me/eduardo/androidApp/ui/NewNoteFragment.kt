package me.eduardo.androidApp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.eduardo.androidApp.R
import me.eduardo.androidApp.databinding.FragmentNewNoteBinding


class NewNoteFragment : Fragment() {

    private lateinit var vm: NewNoteViewModel
    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            .create(NewNoteViewModel::class.java)

        vm.newNoteCreated.observe(viewLifecycleOwner, Observer { success ->

            if (success) {
                Toast.makeText(context, R.string.note_msg_save, Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()

            } else {
                Toast.makeText(context, R.string.note_msg_NotSaved, Toast.LENGTH_SHORT).show()
            }

        })

        binding.fabSaveNote.setOnClickListener {

            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            val title = binding.etNewNoteTitle.text.toString().trimEnd()
            val body = binding.etNewNoteBody.text.toString().trimEnd()

            if (title.isEmpty()) {
                Toast.makeText(context, R.string.note_msg_validation, Toast.LENGTH_SHORT)
                    .show()
            } else {
                vm.saveNote(title, body)
            }

        }


        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}