package domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class MedicalRecord(
    val id: String,
    val patientId: String,
    val diagnosis: String,
    val treatment: String,
    val dateOfVisit: LocalDateTime,
)
