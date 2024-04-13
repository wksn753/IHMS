package data.repository.patientReopsitory

import domain.model.InsurancePatientRecords
import domain.repository.patientReopsitory.PatientDecorator


class InsurancePatientsRecordsImp:PatientDecorator {
    private val insurancePatients = listOf(
        InsurancePatientRecords(id = "1",name="Patient one", insuranceCompany = "ABC Company", patientPackage = "Basic", expiryDate = "20/02/2018"),
        InsurancePatientRecords(id = "2",name="Patient two", insuranceCompany = "Dhl Company", patientPackage = "Premium", expiryDate = "20/02/2018"),
        InsurancePatientRecords(id = "3",name="Patient three", insuranceCompany = "Airtel ", patientPackage = "Basic", expiryDate = "20/02/2018"),
        InsurancePatientRecords(id = "4",name="Patient four", insuranceCompany = "Mtn", patientPackage = "Basic", expiryDate = "20/02/2018"),
        InsurancePatientRecords(id = "5",name="Patient five", insuranceCompany = "ABC Company", patientPackage = "Premium", expiryDate = "20/02/2018"),
        InsurancePatientRecords(id = "6",name="Patient six", insuranceCompany = "ABC Company", patientPackage = "Premium", expiryDate = "20/02/2018")

    )

    override fun getInsurancePatientsRecords(patientId: String): InsurancePatientRecords? {
        return insurancePatients.find { it.id == patientId }
    }
}