package data.repository.messageRepository

import utils.singleton.IHMSDatabase
import domain.model.Message
import domain.model.User
import domain.repository.messagingRepository.IMessagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.Users

class MessagingRepositoryImpl(private val db: IHMSDatabase) : IMessagingRepository {
    override suspend fun sendMessage(message: Message): Flow<Boolean> =flow {
        db.messages.add(message)
        emit(true)
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

    override  fun setCurrentMessageReceiver(user:Users):Users{
        db.currentReceiver = user
        return db.currentReceiver
    }

    override fun getCurrentMessageReceiver(): Users {
        return  db.currentReceiver
    }
}