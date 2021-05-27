package me.eduardo.androidApp.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
import me.eduardo.androidApp.constants.Constants
import me.eduardo.androidApp.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    private lateinit var vm: EditNoteViewModel
    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val myBundle = arguments
        val id = myBundle?.getLong(Constants.BUNDLE_TAG)

        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            .create(EditNoteViewModel::class.java)

        vm.note.observe(viewLifecycleOwner, Observer { note ->

            binding.etUpdateNoteTitle.setText(note.title)
            binding.etUpdateNoteBody.setText(note.body)

        })

        vm.uploaded.observe(viewLifecycleOwner, Observer { success ->

            if (success) {
                Toast.makeText(context, R.string.note_msg_Update, Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(context, R.string.note_msg_NotUpdated, Toast.LENGTH_SHORT).show()
            }

        })

        vm.deleted.observe(viewLifecycleOwner, Observer { success ->

            if (success) {
                Toast.makeText(context, R.string.note_msg_Delete, Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(context, R.string.note_msg_NotDeleted, Toast.LENGTH_SHORT).show()
            }

        })

        binding.fabUpdateNote.setOnClickListener {

            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            val title = binding.etUpdateNoteTitle.text.toString().trimEnd()
            val body = binding.etUpdateNoteBody.text.toString().trimEnd()

            if (title.isEmpty()) {
                Toast.makeText(context, R.string.note_msg_validation, Toast.LENGTH_SHORT)
                    .show()
            } else {
                id?.let {
                    vm.updateNote(title, body, it)
                }
            }
        }

        binding.fabDeleteNote.setOnClickListener {
            callAlert(id)?.show()
        }

        id?.let {
            vm.getNote(it)
        }

        return binding.root
    }

    override fun onDestroyView() {

        _binding = null

        super.onDestroyView()
    }

    fun callAlert(noteId: Long?): AlertDialog? {
        return activity?.let {
            val alertBuilder = AlertDialog.Builder(it)

            alertBuilder.apply {
                setMessage(R.string.delete_comfirmation_dialog_msg)

                setPositiveButton(R.string.yes,
                    DialogInterface.OnClickListener { dialog, id ->
                        noteId?.let {
                            vm.deleteNote(it)
                        }
                    })

                setNegativeButton(R.string.no,
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(context, R.string.note_msg_NotDeleted2, Toast.LENGTH_SHORT)
                            .show()
                    })

            }


            alertBuilder.create()
        }
    }

}