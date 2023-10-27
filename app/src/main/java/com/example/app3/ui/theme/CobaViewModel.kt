package com.example.app3.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.app3.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel : ViewModel() {
    var namaUsr: String by mutableStateOf("")
    private set
    var noTlp: String by mutableStateOf("")
    private set
    var almUsr: String by mutableStateOf("")
        private set
    var mailUsr: String by mutableStateOf("")
        private set
    var jenisKl: String by mutableStateOf("")
    private set
    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun insertData(nm:String, tlp:String, al:String, jk :String, email:String){
        namaUsr= nm;
        noTlp=tlp;
        almUsr=al;
        jenisKl=jk;
        mailUsr=email;
    }

    fun setJenisK(pilihJK:String){
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }
}