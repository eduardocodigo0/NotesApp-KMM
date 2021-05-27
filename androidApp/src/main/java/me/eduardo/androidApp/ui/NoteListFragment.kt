package me.eduardo.androidApp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import me.eduardo.androidApp.R
import me.eduardo.androidApp.adapter.NoteListAdapter
import me.eduardo.androidApp.constants.Constants
import me.eduardo.androidApp.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {

    private lateinit var vm: NoteListViewModel
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            .create(NoteListViewModel::class.java)

        viewLifecycleOwner.lifecycle.addObserver(vm)

        vm.notesList.observe(viewLifecycleOwner, Observer { notes ->

            if(notes.isNotEmpty()) {

                binding.tvNoMsg.visibility = View.GONE
                binding.rvNotes.visibility = View.VISIBLE

                binding.rvNotes.apply {

                    adapter = NoteListAdapter(notes) { id ->

                        val bundle = Bundle()
                        bundle.putLong(Constants.BUNDLE_TAG, id)
                        Navigation.findNavController(requireActivity().findViewById(R.id.frag_nav_host))
                            .navigate(
                                R.id.action_noteListFragment_to_editNoteFragment,
                                bundle
                            )

                    }
                }
            }else{
                binding.tvNoMsg.visibility = View.VISIBLE
                binding.rvNotes.visibility = View.GONE
            }
        })

        binding.fabNewNote.setOnClickListener {
            Navigation.findNavController(requireActivity().findViewById(R.id.frag_nav_host))
                .navigate(
                    R.id.action_noteListFragment_to_newNoteFragment
                )
        }

        return binding.root
    }


    override fun onDestroyView() {
        binding.rvNotes.adapter = null
        _binding = null

        super.onDestroyView()
    }


}