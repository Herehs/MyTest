package com.testapp.presentation.resultsscreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    trackColor: Color = MaterialTheme.colorScheme.onSecondary,
    indicatorSize: Dp = 100.dp,
    indicatorWidth: Float = 100f,
    gapSize: Float = 0f,
    progress: () -> Float,
    content: @Composable () -> Unit
){
    val indicatorProgress = progress()
    val currProgress = if (indicatorProgress <= 1f) { indicatorProgress * 216 } else {216f}
    val foregroundWidth = indicatorWidth - gapSize
    Column(modifier = Modifier
        .size(indicatorSize)
        .drawBehind {
            background(backgroundColor = color, componentSize = size, width = indicatorWidth)
            foreground(
                foregroundColor = trackColor,
                componentSize = size,
                width = foregroundWidth,
                angle = currProgress
            )
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}


fun DrawScope.background(backgroundColor: Color, componentSize: Size, width: Float){
    drawArc(
        size = size,
        color = backgroundColor,
        style = Stroke(width = width, cap = StrokeCap.Round),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        ),
        startAngle = 72f + 90f,
        sweepAngle = 216f,
        useCenter = false
    )
}

fun DrawScope.foreground(foregroundColor: Color, componentSize: Size, width: Float, angle: Float){
    drawArc(
        size = size,
        color = foregroundColor,
        style = Stroke(width = width, cap = StrokeCap.Round),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        ),
        startAngle = 72f + 90f,
        sweepAngle = angle,
        useCenter = false
    )

}


@Preview(showBackground = true)
@Composable
fun test(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularIndicator(progress = {0.5f}, indicatorSize = 200.dp, gapSize = 20f){
            Text("zalupa")
        }
    }
}