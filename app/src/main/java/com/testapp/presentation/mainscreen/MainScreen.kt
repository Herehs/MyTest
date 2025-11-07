package com.testapp.presentation.mainscreen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.testapp.vm.TestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    vm: TestViewModel,
    goToResultScreen: () -> Unit
) {
    val count by vm.count.collectAsState()
    val quiz by vm.quiz.collectAsState()
    val size = quiz.questions.size
    val userSelected by vm.userSelected.collectAsState()

    Scaffold(topBar = {
        val animatedProgress by animateFloatAsState(
            targetValue = count.toFloat()/size,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )
        CenterAlignedTopAppBar(
            title = {
                LinearProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.onPrimary,
                    progress = { animatedProgress }
                )
            },
            actions = {
                Text(modifier = Modifier.padding(end = 16.dp),
                    text = "${count}/${size}"
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
                actionIconContentColor = MaterialTheme.colorScheme.primary
            ),
        )

    }, floatingActionButton = {
        AnimatedVisibility(
            visible = count == size,
            enter = scaleIn() + fadeIn(),
        ) {
            Button(
                onClick = {
                    goToResultScreen()
                    vm.checkAnswers()
                },
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
            ) {
                Text(text = "Завершить", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }, floatingActionButtonPosition = FabPosition.Center
    ){ innerPadding ->

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(quiz.questions){ index, question ->
                TestCard(que = question,
                    selectedAnswer = userSelected[index] ,
                    onAnswerSelect = { currAnswer ->
                        vm.increaseCount(index)
                        vm.onAnswerSelected(index, currAnswer)
                    },
                    click = {}
                )
            }
        }
    }
}