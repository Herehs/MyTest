package com.testapp.presentation.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.testapp.util.Question

@Composable
fun TestCard(
    que: Question,
    selectedAnswer: String?,
    onAnswerSelect: (String) -> Unit,
    click: () -> Unit
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp)
        .border(width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer)
    ){

        Column(modifier = Modifier.selectableGroup()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                .padding(8.dp)){
                Text(
                    text = que.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            }
            var selectedOnce by rememberSaveable { mutableStateOf(false) }
            que.answers.forEach { text ->
                val isSelected = text == selectedAnswer
                Row(modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onAnswerSelect(text)
                            if(!selectedOnce) {
                                click()
                                selectedOnce = true
                            }
                        }
                    )
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    RadioButton(
                        modifier = Modifier.padding(end = 8.dp),
                        selected = (isSelected),
                        onClick = {
                            onAnswerSelect(text)
                            if(!selectedOnce) {
                                click()
                                selectedOnce = true
                            }

                        }
                    )
                    Text(text = text, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}