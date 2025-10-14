package com.testapp.util

import android.content.Context
import kotlinx.serialization.json.Json
import java.io.IOException

fun loadJsonFromRaw(context: Context, resourceId: Int): String {
    return try {
        context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    } catch (e: IOException) {
        e.printStackTrace()
        ""
    }
}




fun parseQuestions(string: String): Quiz {
    return Json.decodeFromString<Quiz>(string)
}