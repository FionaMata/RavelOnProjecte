package com.mataecheverry.project_ravelry.ui.viewmodels

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mataecheverry.project_ravelry.dades.autenticacio.FirestoreManager
import com.mataecheverry.project_ravelry.dades.xarxa.api.RavelryClient
import com.mataecheverry.project_ravelry.dades.xarxa.api.RavelryHelperImpl
import com.mataecheverry.project_ravelry.models.api_models.toAppPattern
import com.mataecheverry.project_ravelry.models.app_models.AppPattern
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelPatterns(): ViewModel() {
    var _state = MutableStateFlow(PatternsState())
    val state: StateFlow<PatternsState> = _state.asStateFlow()
    val fsm: FirestoreManager = FirestoreManager()
    val apiHelper = RavelryHelperImpl(RavelryClient.service)

    init {
        loadPatterns()
    }

    fun loadPatterns() {
        _state.value = state.value.copy(loading = true)
        viewModelScope.launch {
            apiHelper.getPatterns()
                .collect() { result ->
                    _state.value = state.value.copy(
                        loading = false,
                        patterns = result.patterns.map {
                            it.toAppPattern()
                        }
                    )
                }
        }
    }
}


data class PatternsState(
    val loading: Boolean = true,
    val patterns: List<AppPattern> = listOf(),
    val sort: String = "",
    val page: Int = 1,
)