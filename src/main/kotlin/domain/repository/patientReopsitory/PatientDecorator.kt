package domain.repository.patientReopsitory

import domain.model.InsurancePatientRecords

interface PatientDecorator {
    fun getInsurancePatientsRecords(patientId:String): InsurancePatientRecords?
}