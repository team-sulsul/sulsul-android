package com.sulsul.feature.calendar.drink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.local.repository.RecordRepository
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
    private val repository: RecordRepository
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

    fun insertDrinkRecord(record: DrinkRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRecord(record)
        }
    }

    fun deleteDrinkRecord(date: LocalDate) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecord(date)
        }
    }
}
