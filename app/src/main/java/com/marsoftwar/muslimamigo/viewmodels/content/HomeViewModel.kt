package com.marsoftwar.muslimamigo.viewmodels.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsoftwar.muslimamigo.data.local.room.Douae
import com.marsoftwar.muslimamigo.data.local.room.DouaeDao
import com.marsoftwar.muslimamigo.data.local.room.DouaeEntity
import com.marsoftwar.muslimamigo.data.local.room.toDouae
import com.marsoftwar.muslimamigo.data.repository.DouaeRepository
import com.marsoftwar.muslimamigo.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val repository: DouaeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState:StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            //repository.deleteAllDouaes()
            repository.getAllDouaesFromDb().collectLatest { douaes ->
                _uiState.update {
                    it.copy(douaes = douaes)
                }
            }
        }
    }




}

