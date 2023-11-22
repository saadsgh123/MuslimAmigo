package com.marsoftwar.muslimamigo.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.marsoftwar.muslimamigo.data.local.room.Douae
import com.marsoftwar.muslimamigo.data.remote.FirebaseSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository@Inject constructor(
    private val repository: DouaeRepository,
    private val source: FirebaseSource
) {

    suspend fun mergingData() {
        if (shouldInsertNewData()) {
            val existingDouaes = repository.getAllDouaesFromDb()
                .first()

            val newDouaes = source.getAllDouaes().filterNot { existingDouaes.contains(it) }

            newDouaes.forEach {
                repository.insertDouae(it)
                Log.d("saad", "Data is inserting to the database")
            }
        }
    }

    private suspend fun shouldInsertNewData(): Boolean {
        val existingDouaes = repository.getAllDouaesFromDb()
            .first() // Collect the data from the flow

        return existingDouaes.isEmpty() || !existingDouaes.containsAll(source.getAllDouaes())
    }
}
