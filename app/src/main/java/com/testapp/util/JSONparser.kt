package com.testapp.util

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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

