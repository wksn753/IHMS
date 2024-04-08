import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.navigation.NavController
import ui.navigation.NavigationHost
import ui.navigation.composable
import ui.screens.HomeScreen.HomeScreen
import ui.screens.PatientsScreen.PatientsScreen
import androidx.compose.ui.Modifier
import data.repository.patientReopsitory.InsurancePatientsRecordsImp
import data.repository.patientReopsitory.PatientRepositoryImpl
import domain.model.Screen
import domain.repository.patientReopsitory.PatientDecorator
import domain.repository.patientReopsitory.PatientRepository
import ui.navigation.rememberNavController
import ui.screens.Insurance.InsuranceScreen
import ui.screens.PatientsScreen.patientScreenController.PatientScreenController

@Composable
@Preview
fun App() {
    val patientRepository: PatientRepository = PatientRepositoryImpl()
    val patientDecorator:PatientDecorator = InsurancePatientsRecordsImp()
    val allPatients = patientRepository.getAllPatients()
    val screens = Screen.values().toList()
    val navController by rememberNavController(Screen.HomeScreen.name)
    val currentScreen by remember {
        navController.currentScreen
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    CustomNavigationHost(navController = navController,patientRepository=patientRepository,patientDecorator=patientDecorator)
                }
                NavigationRail(
                    modifier = Modifier.align(Alignment.CenterStart).fillMaxHeight()
                ) {
                    screens.forEach {
                        NavigationRailItem(
                            selected = currentScreen == it.name,
                            icon = {
                                Icon(
                                    imageVector = it.icon,
                                    contentDescription = it.label
                                )
                            },
                            label = {
                                Text(it.label)
                            },
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigate(it.name)
                            }
                        )
                    }
                }
            }
        }
    }
}




@Composable
fun CustomNavigationHost(
    navController: NavController,patientRepository: PatientRepository,patientDecorator: PatientDecorator
) {
    NavigationHost(navController) {
        composable(Screen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(Screen.PatientScreen.name){
            val patitentScreenController = PatientScreenController(patientRepository = patientRepository)
            val patientsScreenState by patitentScreenController.patientsScreenState.collectAsState()
            val patients = patientsScreenState.patients
            PatientsScreen(navController = navController, patients = patients, addPatients = {patitentScreenController.addPatient(it)})
        }

        composable(Screen.InsuranceScreen.name){
            InsuranceScreen(patientDecorator = patientDecorator)
        }


    }.build()
}
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
