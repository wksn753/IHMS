package data.repository.messageRepository

import utils.IHMSDatabase
import domain.model.Message
import domain.model.User
import domain.repository.messagingRepository.IMessagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MessagingRepositoryImpl(private val db: IHMSDatabase) : IMessagingRepository {
    override  fun sendMessage(message: Message): Message {
        db.messages.add(message)
        return message
    }

    override  suspend fun getAllMessages(receiverId:String): Flow<List<Message>> = flow{
        val messages = db.messages.filter{messages -> (messages.receiverId == receiverId) || (messages.senderId==receiverId)}
         emit(messages.toList())
    }

    override suspend fun getMessageById(id: String): Flow<List<Message>> =flow {
        val message = db.messages.filter { message -> message.senderId == id }.toList().also {
            emit(it)
        }
    }

    override  fun setCurrentMessageReceiver(user:User):User{
        db.currentReceiver = user
        return db.currentReceiver
    }

    override fun getCurrentMessageReceiver():User{
        return  db.currentReceiver
    }
}