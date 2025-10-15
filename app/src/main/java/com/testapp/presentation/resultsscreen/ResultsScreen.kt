package com.testapp.presentation.resultsscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.testapp.vm.TestViewModel

@Composable
fun ResultsScreen(vm: TestViewModel = viewModel()){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        LaunchedEffect(true) {
            vm.checkAnswers()
        }
        val correctAnswersCount by vm.correctAnswers.collectAsState()

        var currentProgress by remember { mutableStateOf(0f) }
        val animatedProgress by animateFloatAsState(
            targetValue = currentProgress,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )

        CircularIndicator(
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.onPrimary,
            progress = {animatedProgress},
            gapSize = 20f,
            indicatorSize = 300.dp,
            indicatorWidth = 150f
        ){
            Text("${correctAnswersCount}", style = MaterialTheme.typography.headlineLarge)
            Button(onClick = {currentProgress += 0.1f}) { }
        }
    }
}