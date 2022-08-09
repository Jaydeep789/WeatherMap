package com.example.weathermap.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.weathermap.R
import com.example.weathermap.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        (activity as AppCompatActivity).supportActionBar?.title = args.city
        val data = args.weather

        binding.apply {
            temperature.text = data.main.temp.toString()
            feelsLike.text = getString(R.string.feels_like, data.main.feels_like.toString())
            clouds.text = data.weather.first().main
            brokenClouds.text = data.weather.first().description
        }
    }
}

