package cn.edu.sicnu.cs.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.cs.weatherapp.model.City
import cn.edu.sicnu.cs.weatherapp.util.RawReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * 主界面：在子线程中读取本地城市列表 JSON 文件，使用 GSON 解析后在 RecyclerView 中显示；
 * 点击某个城市跳转到天气详情界面。
 */
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvEmpty: TextView
    private val cityList = mutableListOf<City>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        tvEmpty = findViewById(R.id.tvEmpty)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        loadCities()
    }

    /**
     * 采用多线程方式读取本地 citycode.json，避免阻塞主线程；解析完成后回到主线程刷新列表。
     */
    private fun loadCities() {
        progressBar.visibility = android.view.View.VISIBLE
        Thread {
            val json = RawReader.readFileFromRaw(this@MainActivity, R.raw.city_code) ?: ""
            val type = object : TypeToken<List<City>>() {}.type
            val parsed: List<City> = try {
                Gson().fromJson(json, type) ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
            // 只保留带有城市编码、可查询天气的城市
            val valid = parsed.filter { !it.city_code.isNullOrBlank() }

            runOnUiThread {
                progressBar.visibility = android.view.View.GONE
                cityList.clear()
                cityList.addAll(valid)
                if (cityList.isEmpty()) {
                    tvEmpty.visibility = android.view.View.VISIBLE
                    tvEmpty.text = "城市列表加载失败"
                } else {
                    recyclerView.adapter = CityAdapter(cityList) { city ->
                        val intent = Intent(this@MainActivity, WeatherActivity::class.java).apply {
                            putExtra("city_code", city.city_code)
                            putExtra("city_name", city.city_name)
                        }
                        startActivity(intent)
                    }
                }
            }
        }.start()
    }
}
