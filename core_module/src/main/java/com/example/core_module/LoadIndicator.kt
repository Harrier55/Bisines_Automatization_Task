package com.example.bisinesautomatizationtask.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoadIndicator() {
    Card() {
        Column(Modifier.padding(16.dp)) {
            CircularProgressIndicator(color = Color.Red)
            Text(
                text = "Загрузка",
                Modifier.padding(top = 4.dp),
                fontSize = 10.sp,

            )
        }
    }
}