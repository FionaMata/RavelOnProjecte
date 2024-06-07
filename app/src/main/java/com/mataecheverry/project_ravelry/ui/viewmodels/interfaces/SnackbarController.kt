package com.mataecheverry.project_ravelry.ui.viewmodels.interfaces

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun renderSnackbar(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    message: String,
    goBack: () -> Unit
){
    coroutineScope.launch {
        //val action =
        snackbarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short,
            withDismissAction = true,
        )
        goBack()
    }
}