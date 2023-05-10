package com.brandonhxrr.simpletask.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.brandonhxrr.simpletask.R

val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.product_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.product_sans_regular)),
        fontWeight = FontWeight.Normal,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.product_sans_bold))
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.product_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
)