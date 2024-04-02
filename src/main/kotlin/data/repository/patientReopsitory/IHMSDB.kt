package data.repository.patientReopsitory

import domain.model.Message
import domain.model.Patient
import domain.model.User

object IHMSDB {

    val patients = mutableListOf<Patient>()
    val messages = mutableListOf<Message>()
    val users = mutableListOf<User>()

}