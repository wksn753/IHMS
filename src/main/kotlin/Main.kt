import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import domain.model.Patient
import ui.navigation.NavController
import ui.navigation.NavigationHost
import ui.navigation.composable
import ui.screens.HomeScreen.HomeScreen
import ui.screens.PatientsScreen.PatientsScreen
import androidx.compose.ui.Modifier
import data.repository.patientReopsitory.IHMSDB
import data.repository.patientReopsitory.PatientRepositoryImpl
import data.repository.userRepository.UserRepositoryImpl
import domain.model.Screen
import domain.repository.patientReopsitory.PatientRepository
import domain.repository.patientReopsitory.UserFactory
import ui.IHMSTheme
import ui.navigation.rememberNavController
import ui.screens.PatientsScreen.patientScreenController.PatientScreenController
import ui.screens.UsersScreen.UserController
import ui.screens.UsersScreen.UsersScreen

@Composable
@Preview
fun App() {
    val patientRepository: PatientRepository = PatientRepositoryImpl(IHMSDB)
    val allPatients = patientRepository.getAllPatients()
    val screens = Screen.entries.toList()
    val navController by rememberNavController(Screen.HomeScreen.name)
    val currentScreen by remember {
        navController.currentScreen
    }

    IHMSTheme {
        Surface(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    CustomNavigationHost(navController = navController,patientRepository=patientRepository)
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
    navController: NavController,patientRepository: PatientRepository
) {
    NavigationHost(navController) {
        composable(Screen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(Screen.UserScreen.name){
            val userRepository = UserRepositoryImpl(IHMSDB)
            val userFactory = UserFactory()
            val userController = UserController(userFactory = userFactory, userRepositoryImpl = userRepository)
            val usersScreenState by userController.userScreenState.collectAsState()
            val users = usersScreenState.users
            UsersScreen(navController = navController, users = users, addAdmin = {userController.addAdmin(it)}, addUser = {userController.addMember(it)}, onUserClicked = {})
        }
        composable(Screen.PatientScreen.name){
            val patitentScreenController = PatientScreenController(patientRepository = patientRepository)
            val patientsScreenState by patitentScreenController.patientsScreenState.collectAsState()
            val patients = patientsScreenState.patients
            PatientsScreen(navController = navController, patients = patients, addPatients = {patitentScreenController.addPatient(it)})
        }


    }.build()
}
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
