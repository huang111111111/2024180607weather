package cn.edu.sicnu.cs.weatherapp.model

/**
 * 本地城市列表 JSON（citycode.json）中每一项对应的实体类，使用 GSON 进行解析。
 */
data class City(
    val id: Int = 0,
    val pid: Int = 0,
    val city_code: String? = null,
    val city_name: String? = null,
    val post_code: String? = null,
    val area_code: String? = null,
    val ctime: String? = null
)
