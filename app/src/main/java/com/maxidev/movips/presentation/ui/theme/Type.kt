package com.maxidev.movips.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.maxidev.movips.R

val roboto = FontFamily(
    Font(resId = R.font.roboto_light, weight = FontWeight.Light),
    Font(resId = R.font.roboto, weight = FontWeight.Normal),
    Font(resId = R.font.roboto_medium, weight = FontWeight.Medium)
)

val oswald = FontFamily(
    Font(resId = R.font.oswald, weight = FontWeight.Normal),
    Font(resId = R.font.oswald_medium, weight = FontWeight.Medium),
    Font(resId = R.font.oswald_bold, weight = FontWeight.Bold),
    Font(resId = R.font.oswald_semibold, weight = FontWeight.SemiBold)
)

val bebasNeue = FontFamily(
    Font(resId = R.font.bebas_neue, weight = FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)