package com.maxidev.movips.presentation.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.maxidev.movips.presentation.ui.theme.roboto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarItem(
    modifier: Modifier = Modifier,
    title: String,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                fontSize = 26.sp,
                fontFamily = roboto,
                fontWeight = FontWeight.Medium
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        scrollBehavior = scrollBehavior
    )
}