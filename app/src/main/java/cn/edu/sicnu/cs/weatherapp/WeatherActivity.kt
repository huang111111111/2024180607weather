package cn.edu.sicnu.cs.weatherapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.cs.weatherapp.model.WeatherResponse
import cn.edu.sicnu.cs.weatherapp.util.WeatherIcon
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

/**
 * 天气详情界面：接收上一界面传入的城市编码，使用 Volley 请求天气接口，
 * GSON 解析返回 JSON 后，展示当前天气、实时指标与未来预报。
 */
class WeatherActivity : AppCompatActivity() {

    private lateinit var tvCity: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvType: TextView
    private lateinit var ivWeather: ImageView
    private lateinit var tvShidu: TextView
    private lateinit var tvQuality: TextView
    private lateinit var tvPm25: TextView
    private lateinit var tvGanmao: TextView
    private lateinit var forecastRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvError: TextView

    private val baseUrl = "http://t.weather.itboy.net/api/weather/city/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        tvCity = findViewById(R.id.tvCity)
        tvTemp = findViewById(R.id.tvTemp)
        tvType = findViewById(R.id.tvType)
        ivWeather = findViewById(R.id.ivWeather)
        tvShidu = findViewById(R.id.tvShidu)
        tvQuality = findViewById(R.id.tvQuality)
        tvPm25 = findViewById(R.id.tvPm25)
        tvGanmao = findViewById(R.id.tvGanmao)
        forecastRecyclerView = findViewById(R.id.forecastRecyclerView)
        progressBar = findViewById(R.id.progressBar)
        tvError = findViewById(R.id.tvError)

        forecastRecyclerView.layoutManager = LinearLayoutManager(this)

        val cityCode = intent.getStringExtra("city_code") ?: ""
        val cityName = intent.getStringExtra("city_name") ?: ""
        toolbar.title = cityName
        tvCity.text = cityName

        fetchWeather(cityCode)
    }

    private fun fetchWeather(cityCode: String) {
        progressBar.visibility = android.view.View.VISIBLE
        tvError.visibility = android.view.View.GONE

        val queue = Volley.newRequestQueue(this)
        val url = baseUrl + cityCode

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                progressBar.visibility = android.view.View.GONE
                try {
                    val result: WeatherResponse = Gson().fromJson(response, WeatherResponse::class.java)
                    bindData(result)
                } catch (e: Exception) {
                    e.printStackTrace()
                    showError("天气数据解析失败")
                }
            },
            { error ->
                progressBar.visibility = android.view.View.GONE
                showError("网络请求失败：${error.message ?: "请检查网络后重试"}")
            }
        )
        queue.add(stringRequest)
    }

    private fun bindData(result: WeatherResponse) {
        val data = result.data
        val today = data?.forecast?.firstOrNull()

        tvTemp.text = "${data?.wendu ?: "--"}℃"
        tvType.text = today?.type ?: ""
        ivWeather.setImageResource(WeatherIcon.getResId(today?.type))

        tvShidu.text = data?.shidu ?: "--"
        tvQuality.text = data?.quality ?: "--"
        tvPm25.text = if (data != null) data.pm25.toInt().toString() else "--"
        tvGanmao.text = "感冒提示：${data?.ganmao ?: "--"}"

        val forecasts = data?.forecast ?: emptyList()
        forecastRecyclerView.adapter = ForecastAdapter(forecasts)
    }

    private fun showError(msg: String) {
        tvError.visibility = android.view.View.VISIBLE
        tvError.text = msg
    }
}
