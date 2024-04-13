package facade

import domain.model.Patient

class HospitalInformationFacade(
    private val billingSystem: BillingSystem,
    private val patientRecordsSystem: PatientRecordsSystem
) {
    fun getPatientInfo(patientId: String): Patient? {
        return patientRecordsSystem.getPatientById(patientId)
    }

    fun generateBill(patientId: String, treatment: String): Bill? {
        val patientInfo = patientRecordsSystem.getPatientById(patientId)
        val billAmount = calculateBillAmount(treatment) // Implement logic to calculate bill
        return patientInfo?.let { billingSystem.generateBill(it, treatment, billAmount) }
    }

    fun processPayment(bill: Bill, paymentMethod: String): Boolean {
        return billingSystem.processPayment(bill, paymentMethod)
    }

    fun getBill(billId:String):Bill?{
        return billingSystem.getBillDetails(billId)
    }

    private fun calculateBillAmount(treatment: String): Double {
        // Implement logic to calculate amount based on treatment
        return 8.0
    }
}