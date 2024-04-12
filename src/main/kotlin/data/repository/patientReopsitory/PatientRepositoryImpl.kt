package data.repository.patientReopsitory

import utils.IHMSDatabase
import domain.model.Patient
import domain.repository.patientReopsitory.PatientRepository

class PatientRepositoryImpl constructor(private val db: IHMSDatabase): PatientRepository {


    override fun getAllPatients(): List<Patient> {
        return db.patients.toList()
    }

    override fun getPatientById(id: String): Patient? {
        return db.patients.find { patient -> patient.id == id }
    }

    override fun addPatient(patient: Patient) {
        if (!db.patients.any { it.id == patient.id }) {
            db.patients.add(patient)
        }
    }

    override fun updatePatient(patient: Patient) {
        val index = db.patients.indexOfFirst { it.id == patient.id }
        if (index != -1) {
            db.patients[index] = patient
        }
    }

    override fun deletePatient(id: String) {
        db.patients.removeAll { it.id == id }
    }
}