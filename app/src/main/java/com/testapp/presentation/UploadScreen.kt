package com.testapp.presentation

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.testapp.vm.TestViewModel

@Composable
fun UploadScreen(vm: TestViewModel ,goToMainScreen: () -> Unit){
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            uri?.let{
                try {
                    val contentResolver= context.contentResolver
                    contentResolver.openInputStream(it)?.use{ inputStream ->
                        val text = inputStream.bufferedReader().use { reader ->
                            reader.readText()
                        }
                        vm.LoadUserQuestions(text)
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    )



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = {
            launcher.launch(arrayOf("application/json"))
            goToMainScreen()
                         },
            colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )){
            Text(text = "Выбрать файл", style = MaterialTheme.typography.bodyLarge)
        }
    }
}



//val context = LocalContext.current
//var json by remember { mutableStateOf<String?>(null) }
//val launcher = rememberLauncherForActivityResult(
//    contract = ActivityResultContracts.OpenDocument(),
//    onResult = { uri ->
//        uri?.let{
//            try {
//                val contentResolver= context.contentResolver
//                contentResolver.openInputStream(it)?.use{ inputStream ->
//                    val text = inputStream.bufferedReader().use { reader ->
//                        reader.readText()
//                    }
//                    json = text
//                }
//            }catch (e: Exception){
//                json = "Ошибка чтения файла: ${e.message}"
//            }
//        }
//    }
//)