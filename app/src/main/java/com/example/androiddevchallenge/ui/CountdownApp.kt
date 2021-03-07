package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.CountdownTheme
import com.example.androiddevchallenge.utils.offsetGradientBackground
import com.example.androiddevchallenge.utils.randomLongColorAnimation
import dev.chrisbanes.accompanist.insets.ExperimentalAnimatedInsets
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import kotlinx.coroutines.*

@ExperimentalAnimatedInsets
@Composable
fun CountdownApp() {
    CountdownTheme {
        val infiniteTransition = rememberInfiniteTransition()

        val color by infiniteTransition.randomLongColorAnimation()
        val color2 by infiniteTransition.randomLongColorAnimation()
        val color3 by infiniteTransition.randomLongColorAnimation()
        val color4 by infiniteTransition.randomLongColorAnimation()

        ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offsetGradientBackground(
                        listOf(color, color2)
                    ),
            ) {
                CountdownScreen()
            }

        }
    }
}

@Composable
fun CountdownScreen() {

    var countdownPaused by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()
    var job: Job? = null
    val coroutineCancellationException = CoroutineExceptionHandler { _, _ -> }

    var hourText by remember { mutableStateOf(0) }
    var minuteText by remember { mutableStateOf(0) }
    var secondText by remember { mutableStateOf(0) }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (hourPlus, minutePlus, secondPlus) = createRefs()
        val (hourTextView, minuteTextView, secondTextView) = createRefs()
        val (hourMinus, minuteMinus, secondMinus) = createRefs()
        val (colonOne, colonTwo) = createRefs()
        val (toggleCountdown) = createRefs()

        createHorizontalChain(
            hourTextView,
            colonOne,
            minuteTextView,
            colonTwo,
            secondTextView,
            chainStyle = ChainStyle.Packed
        )

        Button(
            onClick = { ++hourText },
            modifier = Modifier
                .alpha(
                    animateFloatAsState(
                        targetValue = if (countdownPaused) 1f else 0f,
                        tween(500, easing = FastOutSlowInEasing)
                    ).value
                )
                .background(Color.Transparent)
                .constrainAs(hourPlus) {
                    bottom.linkTo(hourTextView.top)
                    start.linkTo(hourTextView.start)
                    end.linkTo(hourTextView.end)
                },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(text = "+", style = CountdownTheme.typography.h3, color = Color.Black)
        }

        Button(
            onClick = {
                if (minuteText == 59) {
                    minuteText = 0
                    ++hourText
                } else ++minuteText
            },
            modifier = Modifier
                .alpha(
                    animateFloatAsState(
                        targetValue = if (countdownPaused) 1f else 0f,
                        tween(500, easing = FastOutSlowInEasing)
                    ).value
                )
                .background(Color.Transparent)
                .constrainAs(minutePlus) {
                    bottom.linkTo(minuteTextView.top)
                    start.linkTo(minuteTextView.start)
                    end.linkTo(minuteTextView.end)
                },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(text = "+", style = CountdownTheme.typography.h3, color = Color.Black)
        }

        Button(
            onClick = {
                if (secondText == 59) {
                    secondText = 0
                    ++minuteText
                } else ++secondText
            },
            modifier = Modifier
                .alpha(
                    animateFloatAsState(
                        targetValue = if (countdownPaused) 1f else 0f,
                        tween(500, easing = FastOutSlowInEasing)
                    ).value
                )
                .background(Color.Transparent)
                .constrainAs(secondPlus) {
                    bottom.linkTo(secondTextView.top)
                    start.linkTo(secondTextView.start)
                    end.linkTo(secondTextView.end)
                },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(text = "+", style = CountdownTheme.typography.h3, color = Color.Black)
        }

        Button(
            onClick = { if (hourText != 0) --hourText },
            modifier = Modifier
                .alpha(
                    animateFloatAsState(
                        targetValue = if (countdownPaused) 1f else 0f,
                        tween(500, easing = FastOutSlowInEasing)
                    ).value
                )
                .background(Color.Transparent)
                .constrainAs(hourMinus) {
                    top.linkTo(hourTextView.bottom)
                    start.linkTo(hourTextView.start)
                    end.linkTo(hourTextView.end)
                },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(text = "-", style = CountdownTheme.typography.h3, color = Color.Black)
        }

        Button(
            onClick = { if (minuteText != 0) --minuteText },
            modifier = Modifier
                .alpha(
                    animateFloatAsState(
                        targetValue = if (countdownPaused) 1f else 0f,
                        tween(500, easing = FastOutSlowInEasing)
                    ).value
                )
                .background(Color.Transparent)
                .constrainAs(minuteMinus) {
                    top.linkTo(minuteTextView.bottom)
                    start.linkTo(minuteTextView.start)
                    end.linkTo(minuteTextView.end)
                },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(text = "-", style = CountdownTheme.typography.h3, color = Color.Black)
        }

        Button(
            onClick = { if (secondText != 0) --secondText },
            modifier = Modifier
                .alpha(
                    animateFloatAsState(
                        targetValue = if (countdownPaused) 1f else 0f,
                        tween(500, easing = FastOutSlowInEasing)
                    ).value
                )
                .background(Color.Transparent)
                .constrainAs(secondMinus) {
                    top.linkTo(secondTextView.bottom)
                    start.linkTo(secondTextView.start)
                    end.linkTo(secondTextView.end)
                },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(text = "-", style = CountdownTheme.typography.h3, color = Color.Black)
        }

        Text(
            text = if (hourText < 10) "0$hourText" else hourText.toString(),
            style = CountdownTheme.typography.h2,
            modifier = Modifier.constrainAs(hourTextView) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            text = ":",
            style = CountdownTheme.typography.h2,
            modifier = Modifier.constrainAs(colonOne) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            text = if (minuteText < 10) "0$minuteText" else minuteText.toString(),
            style = CountdownTheme.typography.h2,
            modifier = Modifier.constrainAs(minuteTextView) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            text = ":",
            style = CountdownTheme.typography.h2,
            modifier = Modifier.constrainAs(colonTwo) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        Text(
            text = if (secondText < 10) "0$secondText" else secondText.toString(),
            style = CountdownTheme.typography.h2,
            modifier = Modifier.constrainAs(secondTextView) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        Button(
            onClick = {
                job?.cancel()
                countdownPaused = countdownPaused.not()
                var totalSeconds = ((hourText * 60 * 60) + (minuteText * 60) + secondText)

                job = scope.async(Dispatchers.IO + coroutineCancellationException) {
                    while (!countdownPaused && totalSeconds != 0) {
                        delay(1000)
                        --totalSeconds
                        Log.e("TAG", "$hourText:$minuteText:$secondText")
                        secondText = totalSeconds % 60
                        val minutes = (totalSeconds - secondText) / 60
                        hourText = minutes / 60
                        minuteText = minutes % 60

                    }
                    if (totalSeconds == 0)
                        countdownPaused = true
                }
            },
            modifier = Modifier
                .navigationBarsPadding()
                .padding(bottom = 16.dp)
                .constrainAs(toggleCountdown) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
        ) {
            Text(
                text = if (countdownPaused) "Begin" else "Pause",
                style = CountdownTheme.typography.h4,
                color = Color.White
            )
        }
    }
}