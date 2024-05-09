package com.sulsul.feature.report.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sulsul.core.data.remote.repository.ReportRepository
import com.sulsul.feature.report.ReportState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportRepository: ReportRepository
) : ViewModel() {

    private val _reportInfo: MutableStateFlow<ReportState> = MutableStateFlow(ReportState.Initial)
    val reportInfo: StateFlow<ReportState> = _reportInfo

    fun getReport(requestDate: String) {
        viewModelScope.launch {
            reportRepository.getReport(requestDate, "accessToken") // Todo : dataStore에서 accessToken가져와야 함
                .catch {e ->
                    _reportInfo.value = ReportState.Failure(e)
                    Timber.d("!!error : $e")
                }.collect {
                    if (it.resultCode.toInt() == 200) {
                        Timber.d("!!success : ${it.resultMessage}")
                        _reportInfo.value = ReportState.Success(it.resultData)
                    } else {
                        _reportInfo.value = ReportState.Loading
                        Timber.d("!!failure : ${it.resultMessage}")
                    }
                }
        }
    }
}
