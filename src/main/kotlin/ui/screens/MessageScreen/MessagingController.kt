package ui.screens.MessageScreen

import domain.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import observer.MessagingObserver

class MessagingController :MessagingObserver{
    private var _messages = MutableStateFlow<List<Message>>(emptyList())

    val messages
        get() = _messages.asStateFlow()
    override fun update(messages: MutableList<Message>) {
        _messages.value = messages;
        _messages.value.forEach {
            msg -> println(msg.message)
        }
    }
}