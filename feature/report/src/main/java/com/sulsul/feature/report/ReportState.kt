package com.sulsul.feature.report

import com.sulsul.core.data.remote.model.response.ReportResult

sealed class ReportState {
    object Initial : ReportState()
    object Loading : ReportState()
    class Failure(val msg: Throwable) : ReportState()
    class Success(val data: ReportResult) : ReportState()
}
