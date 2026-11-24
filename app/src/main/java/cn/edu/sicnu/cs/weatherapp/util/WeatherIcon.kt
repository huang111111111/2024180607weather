package cn.edu.sicnu.cs.weatherapp.util

import cn.edu.sicnu.cs.weatherapp.R

/**
 * 根据天气类型文本匹配对应的本地天气图标资源。
 */
object WeatherIcon {

    fun getResId(type: String?): Int {
        return when {
            type == null -> R.drawable.sun
            type.contains("雷") -> R.drawable.thunder
            type.contains("雨") -> R.drawable.rain
            type.contains("雪") -> R.drawable.snow
            type.contains("阴") -> R.drawable.mcloud
            type.contains("云") -> R.drawable.cloud
            type.contains("晴") -> R.drawable.sun
            else -> R.drawable.sun
        }
    }
}
