package com.mataecheverry.project_ravelry.ui.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mataecheverry.project_ravelry.dades.autenticacio.FirestoreManager
import com.mataecheverry.project_ravelry.models.app_models.AppPattern
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelHome : ViewModel(){
    private var _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()
    val fsm: FirestoreManager = FirestoreManager()
    //val apiHelper = RavelryHelperImpl(RavelryClient.service)

    init {
//        loadHotRightNowPatterns()
//        loadDebutPatterns()
    }

    fun loadHotRightNowPatterns(){
        _state.value = state.value.copy(loading = true)
        viewModelScope.launch {
//            apiHelper.getHotRightNow()
//                .collect(){ result ->
//                    _state.value = state.value.copy(
//                        loading = false,
//                        patterns = result.patterns.map{
//                            it.toAppPattern()
//                        }
//                    )
//                }
        }
    }

    fun loadDebutPatterns(){
        _state.value = _state.value.copy(loading = true)
        viewModelScope.launch {
//            apiHelper.getDebutPatterns()
//                .collect(){ result ->
//                    _state.value = state.value.copy(
//                        loading = false,
//                        patterns = result.patterns.map{
//                            it.toAppPattern()
//                        }
//                    )
//                }
        }
    }

    fun checkForActiveSession(){
        if (FirebaseAuth.getInstance().currentUser != null){
            Log.d("USUARI", "Usuari logged  in")
        }
        else
            Log.d("USUARI", "Usuari no logged  in")
    }
}


data class HomeState(
    val loading: Boolean = true,
    val patterns : List<AppPattern> = listOf(),
    val sort: String = "",
    val page: Int = 1
)