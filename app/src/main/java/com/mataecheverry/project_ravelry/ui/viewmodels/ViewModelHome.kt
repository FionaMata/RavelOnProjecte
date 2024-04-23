package com.mataecheverry.project_ravelry.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
class ViewModelHome : ViewModel(){
    private var _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()
    val fsm: FirestoreManager = FirestoreManager()
    val apiHelper = RavelryHelperImpl(RavelryClient.service)

    init {
        loadHotRightNowPatterns()
        loadDebutPatterns()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadHotRightNowPatterns(){
        _state.value = _state.value.copy(loading = true)
        viewModelScope.launch {
            apiHelper.getHotRightNow()
                .collect(){ result ->
                    _state.value = state.value.copy(
                        loading = false,
                        patterns = result.patterns.map{
                            it.toAppPattern()
                        }
                    )
                }
        }
    }

    fun loadDebutPatterns(){
        _state.value = _state.value.copy(loading = true)
        viewModelScope.launch {
            apiHelper.getDebutPatterns()
                .collect(){ result ->
                    _state.value = state.value.copy(
                        loading = false,
                        patterns = result.patterns.map{
                            it.toAppPattern()
                        }
                    )
                }
        }
    }
}


data class HomeState(
    val loading: Boolean = true,
    val patterns : List<AppPattern> = listOf(),
    val sort: String = "",
    val page: Int = 1
)