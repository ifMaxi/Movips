package com.maxidev.movips.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.maxidev.movips.core.presentation.ui.theme.montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarItem(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                fontSize = 32.sp,
                fontFamily = montserrat
            )
        },
        actions = {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}