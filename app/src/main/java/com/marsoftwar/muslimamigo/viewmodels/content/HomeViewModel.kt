package com.marsoftwar.muslimamigo.viewmodels.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marsoftwar.muslimamigo.data.local.room.DouaeDao
import com.marsoftwar.muslimamigo.data.repository.DouaeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val repository: DouaeRepository
) : ViewModel() {

    init {

    }

}