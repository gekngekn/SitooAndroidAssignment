package com.gekn.sitooandroidassignment.utils

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

fun readJSONFromAssets(context: Context, path: String): String {
    try {
        val file = context.assets.open(path)
        val bufferedReader = BufferedReader(InputStreamReader(file))
        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines ->
            lines.forEach {
                stringBuilder.append(it)
            }
        }

        return stringBuilder.toString()

    } catch (e: Exception) {
        Log.e(
            "readJSONFromAssets",
            "Error reading JSON: $e.",
        )
        e.printStackTrace()
        return ""
    }
}