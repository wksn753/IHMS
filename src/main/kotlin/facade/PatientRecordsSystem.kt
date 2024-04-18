package facade

import domain.model.Patient
import domain.repository.patientReopsitory.PatientRepository

class PatientRecordsSystem : PatientRepository{

    val patients:List<Patient> = listOf(
        Patient(id="1",name="Water Rice", medicalRecords = emptyList(), bill = 0),
        Patient(id="2",name="Peter ii", medicalRecords = emptyList(), bill = 0) ,
        Patient(id="3",name="Makula John", medicalRecords = emptyList(), bill = 0),
        Patient(id="4",name="Patient four", medicalRecords = emptyList(), bill = 0)
    )


    override fun getAllPatients(): List<Patient> {
        TODO("Not yet implemented")
    }

    override fun getPatientById(id: String): Patient? {
        return patients.find { it.id == id }
    }

    override fun addPatient(patient: Patient) {
        TODO("Not yet implemented")
    }

    override fun updatePatient(patient: Patient) {
        TODO("Not yet implemented")
    }

    override fun deletePatient(id: String) {
        TODO("Not yet implemented")
    }

}