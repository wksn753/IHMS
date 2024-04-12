package ui.screens.MessageScreen

import data.repository.userRepository.UserRepositoryImpl
import domain.model.Message
import domain.model.User
import domain.model.UserRole
import domain.repository.messagingRepository.IMessagingRepository
import domain.repository.userRepository.UserRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ui.screens.UsersScreen.UserScreenUiState
import utils.IHMSDatabase
import java.util.UUID

@OptIn(DelicateCoroutinesApi::class)
class MessageController constructor(private val messageReopsitory: IMessagingRepository, private val userRepository:UserRepository) {
    private val _messageUiState = MutableStateFlow<MessageScreenUiState>(MessageScreenUiState(currentUser = User("","",UserRole.MEMBER), allMessages = emptyList(), specificInbox = emptyList(), sendTo =User("","",UserRole.MEMBER), allUsers = emptyList() ))
    private val _messageScreenUiState = MutableStateFlow<MessagingScreenUiState>(MessagingScreenUiState(currentUser = User("","",UserRole.MEMBER), users = emptyList(), currentReceiver = User("","",UserRole.MEMBER), messages = emptyList()))
    val messageUiState
        get() = _messageScreenUiState.asStateFlow()
    init {
        val scope = GlobalScope
        scope.launch {
            userRepository.getAllUsers().collect{
                _messageScreenUiState.update { state -> state.copy(users = it) }
            }
        }
    }

    fun updateCurrentUser(user: User){
        userRepository.setCurrentUser(user)
        _messageScreenUiState.update { state -> state.copy(currentUser = User("Wk","",UserRole.MEMBER)) }
        println(_messageScreenUiState.value.currentUser.username)
    }
    fun setCurrentReceiver(receiver:User){
        messageReopsitory.setCurrentMessageReceiver(receiver)
        _messageScreenUiState.update { state -> state.copy(currentReceiver = messageReopsitory.getCurrentMessageReceiver()) }

    }
    fun sendMessage(message: String){
        val msg = Message(UUID.randomUUID().toString(),IHMSDatabase.getInstance().currentUser.id,IHMSDatabase.getInstance().currentReceiver.id, message)
        val msg2=messageReopsitory.sendMessage(msg)
        _messageScreenUiState.value = _messageScreenUiState.value.copy(messages = IHMSDatabase.getInstance().messages)
        IHMSDatabase.getInstance().messages.forEach{
            msg3 -> println("${msg3.senderId} ${msg3.message}")
        }

    }

}