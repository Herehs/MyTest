package com.testapp.presentation.mainscreen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.testapp.vm.TestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(vm: TestViewModel = viewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        vm.loadQuestions(context)
    }
    val count by vm.count.collectAsState()
    val quiz by vm.quiz.collectAsState()

    val size = quiz.questions.size




    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            modifier = Modifier,
            title = {
                LinearProgressIndicator(color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.onPrimary,
                    progress = { count.toFloat() / size })
            },
            actions = {
                Text(text = "${count}/${size.toInt()}")
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
            enter = slideInHorizontally() + expandHorizontally(expandFrom = Alignment.End)
                    + fadeIn(),
        ) {
            Button(
                onClick = {        },
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
            ) {
                Text(text = "Завершить", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }){ innerPadding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(quiz.questions){ question ->
                TestCard(que = question,
                    onSelect = {selected -> if(selected) vm.increaseCount()}
                )
            }
        }

    }

}