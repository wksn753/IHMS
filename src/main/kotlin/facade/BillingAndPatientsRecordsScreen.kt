package facade

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BillingAndPatientsRecordsScreen(manager: HospitalInformationManager) {
    val facade = manager.facade
    val patient = facade.getPatientInfo("1")
    val bill = facade.getBill("1")

    Column {
        Text(modifier = Modifier.padding(start = 100.dp),
            text = "Patient Name: ${patient?.name}")

        Text(modifier = Modifier.padding(start = 100.dp),
            text = "bill total:${bill?.amount}")
//
//        Button(onClick = {}) {
//            Text("Generate Bill and Pay")
//        }
    }
}