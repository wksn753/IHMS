package domain.repository.patientReopsitory

import domain.model.Patient

interface PatientRepository {
    fun getAllPatients(): List<Patient>
    fun getPatientById(id: String): Patient?
    fun addPatient(patient: Patient)
    fun updatePatient(patient: Patient)
    fun deletePatient(id: String)

}
