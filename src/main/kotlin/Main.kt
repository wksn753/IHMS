import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import ui.screens.MessageScreen.MessageItem
import ui.screens.MessageScreen.MessagingScreen
import ui.screens.MessageScreen.MessagingScreenUiState
import ui.screens.PatientsScreen.patientScreenController.PatientScreenController
import ui.screens.UsersScreen.UserController
import ui.screens.UsersScreen.UsersScreen
import java.util.*

@Composable
fun Messages(messages:List<Message>,onClick:()->Unit){

}
@Composable
@Preview
fun App() {
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
                    CustomNavigationHost(navController = navController,patientRepository=patientRepository,userFactory=userFactory, userRepository = userRepository, messageRepository = messageRepository)
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
    navController: NavController,patientRepository: PatientRepository,userFactory: UserFactory,userRepository: UserRepository,messageRepository: IMessagingRepository
) {
    NavigationHost(navController) {
        composable(Screen.MessageScreen.name){

           val messageController = MessageController(messageRepository,userRepository)
            val messageScreenUiState by messageController.messageUiState.collectAsState()
            var messageState = remember { mutableStateOf(MessagingScreenUiState(currentReceiver = User("","",UserRole.MEMBER), users = emptyList(), currentUser = User("","",UserRole.MEMBER), messages = emptyList()) ) }
            var hasUser by remember { mutableStateOf(false) }
            var viewMessages by remember { mutableStateOf(false) }
            var message by remember { mutableStateOf("") }
            val users =listOf(
                User(id = UUID.randomUUID().toString(),username = "Wassanyi",role = UserRole.MEMBER),
                User(id = UUID.randomUUID().toString(),username = "Kevin",role = UserRole.ADMIN),
                User(id = UUID.randomUUID().toString(),username = "Stephen",role = UserRole.MEMBER))
            var messageList by remember { mutableStateOf<List<Message>>(emptyList()) }

            messageList=IHMSDatabase.getInstance().messages

            MessagingScreen(navController = navController, users = messageScreenUiState.users, messages = messageList, currentUser = IHMSDatabase.getInstance().currentUser, currentMessageReceiver = IHMSDatabase.getInstance().currentReceiver, hasUser = hasUser, setHasUser = {hasUser=it}, message = message, onMessageChange = {message=it}, onSendClick = {messageController.sendMessage(message = message)}, setCurrentUser = {messageController.updateCurrentUser(it)}, setCurrentReceiver = {messageController.setCurrentReceiver(it)}, messageScreenUiState = messageScreenUiState, setViewMessage = {viewMessages=true})
            if(messageList.isNotEmpty()) {
                    messageList.forEach{
                        msg -> MessageItem(currentUser = IHMSDatabase.getInstance().currentUser, message = msg)
                    }
            }



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
    }.build()
}
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
