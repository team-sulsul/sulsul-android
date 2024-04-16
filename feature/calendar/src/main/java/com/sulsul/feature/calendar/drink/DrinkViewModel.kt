package com.sulsul.feature.calendar.drink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.local.repository.DrinkRecordRepository
import com.sulsul.core.model.DrinkRecord
import com.sulsul.feature.calendar.enums.DrinkTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val repository: DrinkRecordRepository
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

    fun addDrinkRecord(data: DrinkRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDrinkRecordWithDrinkInfo(data)
        }
    }
}
