package domain.model

data class Invoice(var id:String,var isPaid:Boolean=false,var amount:Int=0,var patientId:String )
