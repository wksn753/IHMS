package ui.screens.Insurance

import domain.repository.patientReopsitory.PatientDecorator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
class InsuranceController(private val patientDecorator: PatientDecorator) {
    private val _insuranceScreenState =  MutableStateFlow<InsuranceUiState>(InsuranceUiState.nothing)
    val insuranceScreenState:StateFlow<InsuranceUiState> = _insuranceScreenState.asStateFlow()


    fun getPatientInsuranceData(patientId:String){
        _insuranceScreenState.value= InsuranceUiState.Loading
        val patientInsurance = patientDecorator.getInsurancePatientsRecords(patientId)
        if(patientInsurance != null){
            _insuranceScreenState.value = InsuranceUiState.Success(patientInsurance)
        }
    }

}