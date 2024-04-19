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
import model.Users
import ui.screens.UsersScreen.UserScreenUiState
import utils.singleton.IHMSDatabase
import java.util.UUID

@OptIn(DelicateCoroutinesApi::class)
class MessageController constructor(private val messageReopsitory: IMessagingRepository, private val userRepository:UserRepository) {
    private val _messageUiState = MutableStateFlow<MessageScreenUiState>(MessageScreenUiState(currentUser = Users("","",UserRole.MEMBER), allMessages = emptyList(), specificInbox = emptyList(), sendTo =Users("","",UserRole.MEMBER), allUsers = emptyList() ))
    private val _messageScreenUiState = MutableStateFlow<MessagingScreenUiState>(MessagingScreenUiState(currentUser = Users("","",UserRole.MEMBER), users = emptyList(), currentReceiver = Users("","",UserRole.MEMBER)))
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

    fun updateCurrentUser(user: Users){
        userRepository.setCurrentUser(user)
        _messageScreenUiState.update { state -> state.copy(currentUser = Users("Wk","",UserRole.MEMBER)) }
        println(_messageScreenUiState.value.currentUser.name)
    }
    fun setCurrentReceiver(receiver:Users){
        messageReopsitory.setCurrentMessageReceiver(receiver)
        _messageScreenUiState.update { state -> state.copy(currentReceiver = IHMSDatabase.getInstance().currentReceiver) }

    }
    fun sendMessage(message: String){
        val currentUserId = userRepository.getCurrentUser().id
        val currentReceiverId = messageReopsitory.getCurrentMessageReceiver().id
        val msg = Message(UUID.randomUUID().toString(),currentUserId,currentReceiverId, message)
        val scope = GlobalScope
        scope.launch {
            messageReopsitory.sendMessage(msg).collect{
            }
        }
    }

}