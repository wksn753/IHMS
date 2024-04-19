package ui.screens.MessageScreen

import domain.model.User
import model.Users

data class MessagingScreenUiState(var currentUser: Users,var users:List<Users>,var currentReceiver:Users)
