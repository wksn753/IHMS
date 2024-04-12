package ui.screens.MessageScreen

import domain.model.Message
import domain.model.User

data class MessagingScreenUiState(var currentUser: User,var users:List<User>,var currentReceiver:User,var messages:List<Message>)
