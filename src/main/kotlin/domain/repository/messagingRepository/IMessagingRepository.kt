package domain.repository.messagingRepository

import domain.model.Message
import domain.model.User
import kotlinx.coroutines.flow.Flow
import model.Users

interface IMessagingRepository {
    suspend fun sendMessage(message: Message): Flow<Boolean>
    suspend fun getAllMessages(receiverId:String):Flow<List<Message>>
    suspend fun getMessageById(id: String): Flow<List<Message>>
    fun setCurrentMessageReceiver(user: Users):Users
    fun getCurrentMessageReceiver():Users
}