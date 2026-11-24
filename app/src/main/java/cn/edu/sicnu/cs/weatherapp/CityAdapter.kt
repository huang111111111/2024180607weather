package cn.edu.sicnu.cs.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.cs.weatherapp.model.City

/**
 * 城市列表适配器：把解析后的城市数据绑定到列表项视图。
 */
class CityAdapter(
    private val cities: List<City>,
    private val onItemClick: (City) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCityName: TextView = view.findViewById(R.id.tvCityName)
        val tvCityCode: TextView = view.findViewById(R.id.tvCityCode)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.tvCityName.text = city.city_name ?: ""
        holder.tvCityCode.text = city.city_code ?: ""
        holder.itemView.setOnClickListener { onItemClick(city) }
    }

    override fun getItemCount(): Int = cities.size
}
