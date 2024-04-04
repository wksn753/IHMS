package facade

import domain.model.Patient
import java.util.Date

interface BillingSystemInterface {
    fun generateBill(patientInfo: Patient, treatment: String, amount: Double):Bill
    fun getBillDetails(billId: String):Bill?
    fun updateBillStatus(billId: String, newStatus: BillStatus)

    fun processPayment(bill: Bill, paymentMethod: String):Boolean

    fun getPaymentHistory(patientId: String)

    fun getOutstandingBalance(patientId: String)

    fun generateStatement(patientId: String, startDate: Date, endDate: Date)
}