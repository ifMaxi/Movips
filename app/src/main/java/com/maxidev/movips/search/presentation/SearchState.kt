package com.maxidev.movips.search.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SearchState(
    val query: MutableState<String> = mutableStateOf("")
)