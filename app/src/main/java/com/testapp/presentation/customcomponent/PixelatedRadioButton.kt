package com.testapp.presentation.customcomponent

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.testapp.ui.theme.TestTheme

@Composable
fun PixelatedRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabeled: Boolean = true
){
    val color = if(isSystemInDarkTheme()){
        Color.White
    }
    else{
        Color.Black
    }
    Box(
        modifier = modifier
            .padding(start = 15.dp)
            .size(20.dp)
            .border(color = color, width = (1.5).dp)
            .clickable(onClick = {onClick()}),
        contentAlignment = Alignment.Center
    ){

        val animatedProgress by animateFloatAsState(
            targetValue = if(selected) 1f else 0f,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )

        Box(
            modifier = Modifier
                .fillMaxSize(animatedProgress)
                .padding(4.dp)
                .background(color = color)
        )

    }
}



@Preview(showBackground = true)
@Composable
fun Test(){
    TestTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            PixelatedRadioButton(
                selected = false,
                onClick = {}
            )
            RadioButton(onClick = {}, selected = true)
            PixelatedRadioButton(
                selected = true,
                onClick = {}
            )

        }
    }
}