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
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.acos
import kotlin.math.pow

@Composable
fun CircularIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.tertiary,
    trackColor: Color = MaterialTheme.colorScheme.secondary,
    indicatorSize: Dp = 100.dp,
    indicatorWidth: Float = 100f,
    progress: () -> Float,
    content: @Composable () -> Unit
){
    val indicatorProgress = progress()
    val currProgress = if (indicatorProgress <= 1f) { indicatorProgress * 360 } else {360f}
    Column(modifier = Modifier
        .size(indicatorSize)
        .drawBehind {
            background(backgroundColor = trackColor, componentSize = size, width = indicatorWidth, angle = currProgress)
            foreground(
                foregroundColor = color,
                componentSize = size,
                width = indicatorWidth,
                angle = currProgress,
            )
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}


fun DrawScope.background(backgroundColor: Color, componentSize: Size, width: Float, angle: Float){
    val backgroundProgress = 360f - angle
    val gap = width/15
    //val gap = acos((width*2).pow(2) + componentSize.toString().toFloat().pow(2) * 2)
    drawArc(
        size = size,
        color = backgroundColor,
        style = Stroke(width = width, cap = StrokeCap.Round),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        ),
        startAngle = -90f + angle/2 + gap,
        sweepAngle = backgroundProgress - gap * 2,
        useCenter = false
    )
}

fun DrawScope.foreground(foregroundColor: Color, componentSize: Size, width: Float, angle: Float){
    val gap = width/15
    drawArc(
        size = size,
        color = foregroundColor,
        style = Stroke(width = width, cap = StrokeCap.Round, join = StrokeJoin.Miter, miter = 0.1f),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        ),
        startAngle = -90f + angle/2 - gap,
        sweepAngle = -angle + gap * 2,
        useCenter = false
    )

}


@Preview(showBackground = true)
@Composable
fun test(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularIndicator(progress = {0.5f}, indicatorSize = 200.dp, indicatorWidth = 50f){
            Text("zalupa")
        }
    }
}