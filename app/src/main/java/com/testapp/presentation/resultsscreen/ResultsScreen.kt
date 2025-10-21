package com.testapp.presentation.resultsscreen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testapp.vm.TestViewModel
import java.nio.file.WatchEvent

@Composable
fun ResultsScreen(vm: TestViewModel){
    BackHandler {

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        val correctAnswersCount by vm.correctAnswers.collectAsState()
        val quiz by vm.quiz.collectAsState()
        val answers by vm.userSelected.collectAsState()
        val size = quiz.questions.size

        var startAnimation by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            startAnimation = true
        }
        val animatedProgress by animateFloatAsState(
            targetValue = if(startAnimation){ correctAnswersCount.toFloat() / size }else 0f,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(paddingValues = WindowInsets.systemBars.asPaddingValues()).padding(12.dp)
        ) {
            CircularIndicator(
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.onPrimary,
                progress = {animatedProgress},
                indicatorSize = 300.dp,
                indicatorWidth = 50f
            ){
                Text(
                    text = "${correctAnswersCount}/${size}",
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()
                .padding(12.dp)
                .padding(top = 12.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
            ) {
                itemsIndexed(quiz.questions){ index, question ->
                    Column(modifier = Modifier.padding(12.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(
                            color = if(question.answers[question.correctAnswer] == answers[index]){
                                MaterialTheme.colorScheme.onTertiary
                            }
                            else{ MaterialTheme.colorScheme.errorContainer }

                        )
                        .padding(12.dp)
                    ) {
                        Text("$index. ${question.title}")
                        Text("Ваш ответ: ${answers[index]}")
                        Text("Правильный ответ: ${question.answers[question.correctAnswer]}")
                    }

                }
            }
        }
    }
}