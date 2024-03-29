package data.repository.patientReopsitory

import domain.model.Patient
import domain.repository.patientReopsitory.PatientRepository

class PatientRepositoryImpl : PatientRepository {
    private val patients = mutableListOf<Patient>()

    override fun getAllPatients(): List<Patient> {
        return patients.toList()
    }

    override fun getPatientById(id: String): Patient? {
        return patients.find { it.id == id }
    }

    override fun addPatient(patient: Patient) {
        if (!patients.any { it.id == patient.id }) {
            patients.add(patient)
        }
    }

    override fun updatePatient(patient: Patient) {
        val index = patients.indexOfFirst { it.id == patient.id }
        if (index != -1) {
            patients[index] = patient
        }
    }

    override fun deletePatient(id: String) {
        patients.removeAll { it.id == id }
    }
}