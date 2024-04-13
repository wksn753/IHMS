package ui.screens.Insurance

import domain.model.InsurancePatientRecords

sealed class InsuranceUiState {
    object nothing: InsuranceUiState()


    object Loading : InsuranceUiState()
    data class Success(val data: InsurancePatientRecords) : InsuranceUiState()
    data class Error(val exception: Throwable) : InsuranceUiState()
}
