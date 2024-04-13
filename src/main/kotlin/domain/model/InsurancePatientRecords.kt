package domain.model

data class InsurancePatientRecords(
    val id: String="",
    val name: String="",
    val insuranceCompany: String="",
    var patientPackage:String="",
    var expiryDate:String="",
)
