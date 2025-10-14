package com.testapp.presentation.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun TestCard(que: Question, onSelect: (Boolean) -> Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp)
        .border(width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer)
    ){
        var selected by rememberSaveable { mutableStateOf(false) }
        val (selectedOption, onOptionSelected) = rememberSaveable { mutableStateOf("") }
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
            que.answers.forEach { text ->
                Row(modifier = Modifier.padding(4.dp)
                    .fillMaxWidth()
                    .clickable{
                        onOptionSelected(text)
                        if(selected == false){onSelect(true)}
                        selected = true
                    },
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    RadioButton(
                        modifier = Modifier.padding(end = 8.dp),
                        selected = (text == selectedOption),
                        onClick = null
                    )
                    Text(text = text, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}