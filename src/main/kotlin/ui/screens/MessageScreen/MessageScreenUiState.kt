package ui.screens.MessageScreen

import domain.model.Message
import domain.model.User
import domain.model.UserRole


data class MessageScreenUiState(val allMessages: List<Message>, val specificInbox:List<Message>, val currentUser: User, val sendTo:User, val allUsers: List<User>)
