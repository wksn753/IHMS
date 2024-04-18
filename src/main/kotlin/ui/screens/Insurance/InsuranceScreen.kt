package ui.screens.Insurance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.InsurancePatientRecords
import domain.repository.patientReopsitory.PatientDecorator

@Composable
fun InsuranceScreen(patientDecorator: PatientDecorator){
    val insuranceController = InsuranceController(patientDecorator = patientDecorator)
    val insuranceScreenState =   insuranceController.insuranceScreenState.collectAsState()
    var patientInsurance by remember { mutableStateOf(InsurancePatientRecords()) }

    Column(
        Modifier.padding(start=150.dp)
    ) {
        Text(text="Patients Under insurance ")
        CapturePatientName(getPatientInsurance={ insuranceController.getPatientInsuranceData(it)})
        when(insuranceScreenState.value){
            is InsuranceUiState.Error -> {
                Text("Try again!!")
            }
            InsuranceUiState.Loading -> {
                Text("Loading...")
            }
            is InsuranceUiState.Success -> {
                patientInsurance = (insuranceScreenState.value as InsuranceUiState.Success).data
                PatientDetailsCard(patient = (insuranceScreenState.value as InsuranceUiState.Success).data)
            }
            InsuranceUiState.nothing -> {
                Text("Search insurance records")
            }
        }
        PatientDetailsCard(patient = patientInsurance)

    }
}

@Composable
fun PatientDetailsCard(patient: InsurancePatientRecords) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Patient Name: ${patient.name}", style = MaterialTheme.typography.h6)
            Text(text = "ID: ${patient.id}", style = MaterialTheme.typography.body2)
            Text(text = "Package: ${patient.patientPackage}", style = MaterialTheme.typography.body2)
            Text(text = "Expiry Date: ${patient.expiryDate}", style = MaterialTheme.typography.body2)
        }
    }
}


@Composable
fun CapturePatientName(
    getPatientInsurance: (String) -> Unit,
) {
    var patientId by remember { mutableStateOf("") }
    TextField(
        value = patientId,
        onValueChange = { inputText ->
            patientId = inputText
        },
        label = { Text("Patient Id") }
    )

    Button(onClick = {getPatientInsurance(patientId)} ){
        Text("Get insurance")
    }
}