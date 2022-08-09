package com.example.weathermap.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathermap.R
import com.example.weathermap.databinding.FragmentListBinding
import com.example.weathermap.model.WeatherData
import com.example.weathermap.ui.adapter.WeatherDataAdapter
import com.example.weathermap.utils.DataState.*
import com.example.weathermap.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel: WeatherViewModel by viewModels()
    private val args: ListFragmentArgs by navArgs()
    private lateinit var binding: FragmentListBinding
    private lateinit var weatherDataAdapter: WeatherDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getWeatherByCity(args.city)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)

        (activity as AppCompatActivity).supportActionBar?.apply {
            title = args.city
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        weatherDataAdapter = WeatherDataAdapter { weather ->
            navigate(weather)
        }
        binding.recyclerView.adapter = weatherDataAdapter

        viewModel.weatherData.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is Success -> {
                    displayProgressBar(false)
                    weatherDataAdapter.setWeatherData(dataState.data.list)
                }
                is Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is Loading -> {
                    displayProgressBar(true)
                }
            }
        }
    }

    private fun displayProgressBar(shouldDisplay: Boolean) {
        binding.progressBar.visibility = if (shouldDisplay) View.VISIBLE else View.GONE
    }

    private fun navigate(weather: WeatherData) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(weather, args.city)
        findNavController().navigate(action)
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Unknown Error", Toast.LENGTH_SHORT).show()
        }
    }
}

