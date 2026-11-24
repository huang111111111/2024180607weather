package cn.edu.sicnu.cs.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.cs.weatherapp.model.Forecast
import cn.edu.sicnu.cs.weatherapp.util.WeatherIcon

/**
 * 未来天气预报列表适配器。
 */
class ForecastAdapter(private val forecasts: List<Forecast>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    inner class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWeek: TextView = view.findViewById(R.id.tvWeek)
        val ivForecast: ImageView = view.findViewById(R.id.ivForecast)
        val tvType: TextView = view.findViewById(R.id.tvType)
        val tvTemp: TextView = view.findViewById(R.id.tvTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = forecasts[position]
        holder.tvWeek.text = item.week ?: item.ymd ?: ""
        holder.tvType.text = item.type ?: ""
        holder.tvTemp.text = "${item.low ?: ""} / ${item.high ?: ""}"
        holder.ivForecast.setImageResource(WeatherIcon.getResId(item.type))
    }

    override fun getItemCount(): Int = forecasts.size
}
