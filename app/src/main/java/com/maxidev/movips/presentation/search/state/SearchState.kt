package com.maxidev.movips.presentation.search.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SearchState(
    val query: MutableState<String> = mutableStateOf("")
)