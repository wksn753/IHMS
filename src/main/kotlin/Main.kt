import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import data.repository.messageRepository.MessagingRepositoryImpl
import data.repository.patientReopsitory.PatientRepositoryImpl
import data.repository.userRepository.UserRepositoryImpl
import utils.IHMSDatabase
import domain.model.Message
import domain.model.Screen
import domain.model.User
import domain.model.UserRole
import domain.repository.messagingRepository.IMessagingRepository
import domain.repository.patientReopsitory.PatientRepository
import domain.repository.patientReopsitory.UserFactory
import domain.repository.userRepository.UserRepository
import kotlinx.coroutines.launch
import ui.IHMSTheme
import ui.navigation.rememberNavController
import ui.screens.MessageScreen.MessageController
import ui.screens.MessageScreen.MessagingScreen
import ui.screens.PatientsScreen.patientScreenController.PatientScreenController
import ui.screens.UsersScreen.UserController
import ui.screens.UsersScreen.UsersScreen
import java.util.*
import facade.BillingAndPatientsRecordsScreen
import facade.BillingSystem
import facade.HospitalInformationManager
import facade.PatientRecordsSystem
import data.repository.patientReopsitory.InsurancePatientsRecordsImp
import domain.repository.patientReopsitory.PatientDecorator
import ui.screens.Insurance.InsuranceScreen
@Composable
@Preview
fun App() {

  val patientDecorator:PatientDecorator = InsurancePatientsRecordsImp()
    val allPatients = patientRepository.getAllPatients()
    val patientRepository: PatientRepository = PatientRepositoryImpl(IHMSDatabase.getInstance())
    val userRepository:UserRepository = UserRepositoryImpl(IHMSDatabase.getInstance())
    val messageRepository:IMessagingRepository = MessagingRepositoryImpl(IHMSDatabase.getInstance())
    val screens = Screen.entries.toList()
    val userFactory = UserFactory()
    val navController by rememberNavController(Screen.HomeScreen.name)
    val currentScreen by remember {
        navController.currentScreen
    }

    IHMSTheme {
        Surface(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ) {
            Box(
                modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(0.91f).align(Alignment.CenterEnd)
                ) {

                    CustomNavigationHost(navController = navController,patientRepository=patientRepository,userFactory=userFactory, userRepository = userRepository, messageRepository = messageRepository,patientDecorator=patientDecorator)
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
    navController: NavController,patientRepository: PatientRepository,userFactory: UserFactory,userRepository: UserRepository,messageRepository: IMessagingRepository,patientDecorator: PatientDecorator
) {
    NavigationHost(navController) {
        composable(Screen.MessageScreen.name){

           val messageController = MessageController(messageRepository,userRepository)
            val messageScreenUiState by messageController.messageUiState.collectAsState()
            var hasUser by remember { mutableStateOf(false) }
            MessagingScreen(navController = navController, users = messageScreenUiState.users, messages = emptyList(), currentUser = messageScreenUiState.currentUser, currentMessageReceiver = IHMSDatabase.getInstance().currentReceiver, hasUser = hasUser, setHasUser = {hasUser=it}, message = "", onMessageChange = {}, onSendClick = {}, setCurrentUser = {messageController.updateCurrentUser(it)}, setCurrentReceiver = {messageController.setCurrentReceiver(it)})
        }
        composable(Screen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(Screen.UserScreen.name){
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
        composable(Screen.BillingAndRecords.name) {
            BillingAndPatientsRecordsScreen(manager = HospitalInformationManager(
                billingSystem = BillingSystem(),
                patientRecordsSystem = PatientRecordsSystem()
            ))
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
