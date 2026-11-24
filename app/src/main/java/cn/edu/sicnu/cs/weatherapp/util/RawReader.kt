package cn.edu.sicnu.cs.weatherapp.util

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 读取 res/raw 目录下的文本文件，返回字符串内容。
 */
object RawReader {

    fun readFileFromRaw(context: Context, rawName: Int): String? {
        return try {
            val inputReader = InputStreamReader(context.resources.openRawResource(rawName), Charsets.UTF_8)
            val bufReader = BufferedReader(inputReader)
            val result = StringBuilder()
            var line: String?
            while (bufReader.readLine().also { line = it } != null) {
                result.append(line)
            }
            bufReader.close()
            result.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}
