package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun CountdownApp() {
    CountdownTheme {
        ProvideWindowInsets(false) {
            Column {
                Text(text = "Hello", style = CountdownTheme.typography.h4)
            }
        }
    }
}