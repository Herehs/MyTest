package com.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.testapp.navigation.NavRoot
import com.testapp.ui.theme.TestTheme
import com.testapp.vm.TestViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<TestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { padding ->
                    padding
                    NavRoot(vm = viewModel)
                }
            }
        }
    }
}