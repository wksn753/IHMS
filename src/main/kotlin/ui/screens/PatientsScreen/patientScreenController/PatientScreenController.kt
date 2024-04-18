package ui.screens.PatientsScreen.patientScreenController

import domain.model.MedicalRecord
import domain.model.Patient
import domain.repository.patientReopsitory.PatientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

class PatientScreenController constructor(private val patientRepository: PatientRepository) {
     private val _patientsScreenState:MutableStateFlow<PatientsScreenUiState> = MutableStateFlow<PatientsScreenUiState>(PatientsScreenUiState(patients = emptyList()))

     val patientsScreenState:StateFlow<PatientsScreenUiState>
         get() = _patientsScreenState.asStateFlow()

     init {
         _patientsScreenState.update { state -> state.copy(patients = patientRepository.getAllPatients()) }
     }
    fun addPatient(name: String,){
        val id=patientRepository.getAllPatients().size + 1
        val medicalRecord =  MedicalRecord(id=id.toString(),patientId=id.toString(),diagnosis="", treatment = "", dateOfVisit = LocalDateTime.now())
        val patient = Patient(name = name,id = id.toString(),)
        patientRepository.addPatient(patient)
        _patientsScreenState.update { state -> state.copy(patients = patientRepository.getAllPatients()) }
    }
}