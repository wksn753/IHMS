package ui.screens.PatientsScreen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.MedicalRecord
import domain.model.Patient
import ui.navigation.NavController


@Composable
fun PatientsScreen(modifier: Modifier=Modifier,patients: List<Patient>,navController: NavController,addPatients:(String)->Unit) {
    var addPatient by remember { mutableStateOf(false) }
    var addPatientName by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().background(color = MaterialTheme.colors.background), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier =Modifier.fillMaxWidth(0.9f), verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier.fillMaxWidth(0.48f).fillMaxHeight()){
                Text(text = "Patients", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                LazyColumn {
                    items(patients) { patient ->
                        PatientItem(patient = patient,navController = navController)
                    }
                }
            }
            Column(modifier = Modifier.fillMaxWidth(0.48f).fillMaxHeight()){
                Row(modifier = Modifier.fillMaxWidth(0.96f).clickable{addPatient=!addPatient}){
                    Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "Add Patient")
                    Text(text = "Add Patient", fontSize = 18.sp, fontWeight= FontWeight.Bold)
                }
                if (addPatient){
                    Row (modifier = Modifier.fillMaxWidth(0.48f)){
                        TextField(value = addPatientName, onValueChange = {addPatientName=it})
                    }
                    Row (modifier = Modifier.fillMaxWidth(0.48f)){
                        Button(onClick ={addPatients(addPatientName)}){
                            Text(text = "Add")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PatientItem(modifier: Modifier=Modifier,patient:Patient,navController: NavController){
    Row(modifier = modifier.fillMaxWidth(0.96f).background(color = MaterialTheme.colors.surface).clip(RoundedCornerShape(12.dp))){
        Text(modifier = Modifier.padding(16.dp),text = "${patient.id}")
        Text(modifier = Modifier.padding(16.dp),text = "${patient.name}")
    }
}

@Preview
@Composable
fun PatientsScreenPreview() {
    MaterialTheme{
        val patients =listOf(
            Patient(id = "1",name = "Wassanyi",),
            Patient(id = "2",name = "Kevin",),
            Patient(id = "3",name = "Stephen",))
        val navController = NavController("")
        PatientsScreen(modifier = Modifier.fillMaxSize(),patients = patients, navController = navController, addPatients = {})
    }
}