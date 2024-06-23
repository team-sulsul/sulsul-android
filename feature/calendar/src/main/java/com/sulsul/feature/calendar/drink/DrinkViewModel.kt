package com.sulsul.feature.calendar.drink

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.local.repository.RecordLocalRepository
import com.sulsul.core.model.DrinkInfo
import com.sulsul.core.model.DrinkRecord
import com.sulsul.feature.calendar.enums.DrinkTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val localRepository: RecordLocalRepository,
    private val remoteRepository: com.sulsul.core.data.remote.repository.RecordRemoteRepository
) : ViewModel() {

    val drinkThemeList = listOf(
        DrinkTheme.SOJU,
        DrinkTheme.BEER,
        DrinkTheme.SOJUBEER,
        DrinkTheme.WINE,
        DrinkTheme.RICE_WINE,
        DrinkTheme.COCKTAIL,
        DrinkTheme.WHISKY,
        DrinkTheme.VODKA,
        DrinkTheme.SAKE
    )

    var drinks = mutableListOf<DrinkInfo>()
    var recordId = 0

    override fun onCleared() {
        super.onCleared()
        Log.d("DrinkViewModel", "onCleared")
    }

    /**
     * Local DB
     */
    fun insertLocalDrinkRecord(record: DrinkRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.insertRecord(record)
        }
    }

    fun updateLocalDrinks(recordId: Int, drinks: List<DrinkInfo>) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.updateDrinks(recordId, drinks)
        }
    }

    fun deleteLocalDrinkRecord(date: LocalDate) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.deleteRecord(date)
        }
    }

    /**
     * Remote Server
     */
//    fun postDrinkRecord(record: DrinkRecord) {
//        viewModelScope.launch(Dispatchers.IO) {
//            remoteRepository.postDrinkRecord(record)
//                .collect {
//                    Log.d("###", "code : $it")
//                }
//        }
//    }
//
//    // 수정
//
//    fun deleteDrinkRecord(date: LocalDate) {
//        viewModelScope.launch(Dispatchers.IO) {
//            remoteRepository.deleteRecord(date)
//        }
//    }
}
