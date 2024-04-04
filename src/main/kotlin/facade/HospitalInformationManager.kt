package facade

class HospitalInformationManager(
    billingSystem: BillingSystem,
    patientRecordsSystem: PatientRecordsSystem
) {
    val facade: HospitalInformationFacade

    init {
        facade = HospitalInformationFacade(billingSystem, patientRecordsSystem)
    }
}