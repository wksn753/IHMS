package domain.model

// Example Entity
data class Patient(
    val id: String,
    val name: String,
    var medicalRecords: List<MedicalRecord> = emptyList(),
    var bill:Int=0
)
