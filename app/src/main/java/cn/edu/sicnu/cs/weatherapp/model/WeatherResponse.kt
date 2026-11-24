package cn.edu.sicnu.cs.weatherapp.model

/**
 * 天气接口 http://t.weather.itboy.net/api/weather/city/{cityCode} 返回数据对应的实体类。
 * 字段名与 JSON 保持一致，由 GSON 自动映射。
 */
data class WeatherResponse(
    val message: String? = null,
    val status: Int = 0,
    val date: String? = null,
    val time: String? = null,
    val cityInfo: CityInfo? = null,
    val data: WeatherData? = null
)

data class CityInfo(
    val city: String? = null,
    val citykey: String? = null,
    val parent: String? = null,
    val updateTime: String? = null
)

data class WeatherData(
    val shidu: String? = null,
    val pm25: Double = 0.0,
    val pm10: Double = 0.0,
    val quality: String? = null,
    val wendu: String? = null,
    val ganmao: String? = null,
    val forecast: List<Forecast>? = null,
    val yesterday: Forecast? = null
)

data class Forecast(
    val date: String? = null,
    val high: String? = null,
    val low: String? = null,
    val ymd: String? = null,
    val week: String? = null,
    val sunrise: String? = null,
    val sunset: String? = null,
    val aqi: Int = 0,
    val fx: String? = null,
    val fl: String? = null,
    val type: String? = null,
    val notice: String? = null
)
