package com.example.weathermap.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathermap.R
import com.example.weathermap.databinding.ListItemBinding
import com.example.weathermap.model.WeatherData

class WeatherDataAdapter(
    private var onItemClicked: ((weather: WeatherData) -> Unit)
) : RecyclerView.Adapter<WeatherDataAdapter.WeatherViewHolder>() {

    private var weatherData = emptyList<WeatherData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    fun setWeatherData(data: List<WeatherData>) {
        weatherData = data
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: WeatherData) {
            binding.apply {
                weatherDescription.text = weather.weather.first().description
                val temp =
                    itemView.context.resources.getString(R.string.temperature, weather.main.temp.toString())
                temperature.text = temp

                root.setOnClickListener {
                    onItemClicked(weather)
                }
            }
        }
    }
}

