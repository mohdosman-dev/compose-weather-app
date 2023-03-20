package com.otaku.clones.weatherapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDataDisplay(
    modifier: Modifier = Modifier,
    value: Int,
    unit: String,
    icon: ImageVector,
    textStyle: TextStyle = TextStyle(),
    iconTint: Color = Color.White,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(25.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$value $unit",
            color = Color.White,
            style = textStyle
        )
    }
}
