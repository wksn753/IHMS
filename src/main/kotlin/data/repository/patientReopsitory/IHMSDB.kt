package data.repository.patientReopsitory

import domain.model.Message
import domain.model.Patient
import domain.model.User
import domain.model.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

object IHMSDB {
    val patients = mutableListOf<Patient>()
    val messages = mutableListOf<Message>()
    var currentUser = mutableListOf<User>()
    var currentMessageReceiver = mutableListOf<User>()
    val users = mutableListOf<User>(User("WASSANYI KEVIN",UUID.randomUUID().toString(),UserRole.MEMBER))
}