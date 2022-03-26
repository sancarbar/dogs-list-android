package org.ada.school.dogsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.ada.school.dogsapp.R
import org.ada.school.dogsapp.data.model.Dog
import org.ada.school.dogsapp.data.repository.remote.DogService
import org.ada.school.dogsapp.data.repository.remote.RetrofitGenerator
import org.ada.school.dogsapp.databinding.FragmentFirstBinding
import org.ada.school.dogsapp.ui.adapter.DogsListAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DogsListFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    val viewModel: DogsListFragmentViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.dogsListLiveData.observe(viewLifecycleOwner) {
                dogsList ->
            binding.recyclerView.adapter = DogsListAdapter(dogsList)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}