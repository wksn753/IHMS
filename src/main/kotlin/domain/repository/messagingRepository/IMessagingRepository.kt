package domain.repository.messagingRepository

import domain.model.Message
import domain.model.User
import kotlinx.coroutines.flow.Flow

interface IMessagingRepository {
    fun sendMessage(message: Message): Message
    suspend fun getAllMessages(receiverId:String):Flow<List<Message>>
    suspend fun getMessageById(id: String): Flow<List<Message>>
    fun setCurrentMessageReceiver(user:User):User
    fun getCurrentMessageReceiver():User
}