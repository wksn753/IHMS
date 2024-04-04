package facade

import domain.model.Patient
import java.util.Date

class BillingSystem:BillingSystemInterface {

    val bills :List<Bill> = listOf(
        Bill(billId="1", date="", patientId="1", amount= 200,),
        Bill(billId="2", date="", patientId="2", amount= 1000,),
        Bill(billId="3", date="", patientId="3", amount= 800,),
        Bill(billId="4", date="", patientId="4", amount= 1400,)
    )
    override fun generateBill(patientInfo: Patient, treatment: String, amount: Double):Bill {
        TODO("Not yet implemented")
    }

    override fun getBillDetails(billId: String):Bill? {
        return bills.find { it.billId==billId }
    }

    override fun updateBillStatus(billId: String, newStatus: BillStatus) {
        TODO("Not yet implemented")
    }

    override fun processPayment(bill: Bill, paymentMethod: String):Boolean {
        TODO("Not yet implemented")
    }

    override fun getPaymentHistory(patientId: String) {
        TODO("Not yet implemented")
    }

    override fun getOutstandingBalance(patientId: String) {
        TODO("Not yet implemented")
    }

    override fun generateStatement(patientId: String, startDate: Date, endDate: Date) {
        TODO("Not yet implemented")
    }
}