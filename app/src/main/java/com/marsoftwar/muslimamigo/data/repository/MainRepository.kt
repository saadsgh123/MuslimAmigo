package com.marsoftwar.muslimamigo.data.repository

import com.marsoftwar.muslimamigo.data.local.room.Douae
import com.marsoftwar.muslimamigo.data.remote.FirebaseSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository@Inject constructor(
    private val repository: DouaeRepository,
    private val source: FirebaseSource
) {

    suspend fun mergingData() {
        val list =
        source.getAllDouaes().map {
            repository.insertDouae(it)
        }
    }

}