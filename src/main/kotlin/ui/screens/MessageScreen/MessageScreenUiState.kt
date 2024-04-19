package ui.screens.MessageScreen

import domain.model.Message
import domain.model.User
import domain.model.UserRole
import model.Users


data class MessageScreenUiState(val allMessages: List<Message>, val specificInbox:List<Message>, val currentUser: Users, val sendTo:Users, val allUsers: List<Users>)
