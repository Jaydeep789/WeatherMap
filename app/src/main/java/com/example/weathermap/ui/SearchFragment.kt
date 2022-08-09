package com.example.weathermap.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.weathermap.R
import com.example.weathermap.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        binding.apply {
            search.setOnClickListener {
                if (editText.text.toString().isEmpty()) {
                    Toast.makeText(requireContext(), "Enter city name", Toast.LENGTH_SHORT).show()
                } else {
                    val city = editText.text.toString()
                    val action = SearchFragmentDirections.actionSearchFragmentToListFragment(city)
                    findNavController().navigate(action)
                }
            }
        }
    }
}

