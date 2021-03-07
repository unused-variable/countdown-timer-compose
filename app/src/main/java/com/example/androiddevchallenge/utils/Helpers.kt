package com.example.androiddevchallenge.utils

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import kotlin.random.Random

const val RANDOM_MAX = 255
const val ANIMATION_DURATION = 3000

fun generateRandomColor() =
    Color(
        red = Random.nextInt(RANDOM_MAX),
        green = Random.nextInt(RANDOM_MAX),
        blue = Random.nextInt(RANDOM_MAX)
    )

fun Modifier.offsetGradientBackground(
    colors: List<Color>,
) = background(
    Brush.linearGradient(
        colors,
        start = Offset.Zero,
        end = Offset.Infinite,
        tileMode = TileMode.Mirror,
    )
)

@Composable
fun InfiniteTransition.randomLongColorAnimation() = this.animateColor(
    initialValue = generateRandomColor(),
    targetValue = generateRandomColor(),
    animationSpec = infiniteRepeatable(
        animation = tween(ANIMATION_DURATION, easing = FastOutSlowInEasing),
        repeatMode = RepeatMode.Reverse
    )
)