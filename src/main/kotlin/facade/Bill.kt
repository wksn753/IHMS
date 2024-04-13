package facade

data class Bill(
    var billId:String="",
    var patientId:String="",
    var amount:Int?=null,
    var date:String=""
)
