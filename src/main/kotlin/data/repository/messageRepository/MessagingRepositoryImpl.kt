package data.repository.messageRepository

import data.repository.patientReopsitory.IHMSDB
import domain.model.Message
import domain.repository.messagingRepository.IMessagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MessagingRepositoryImpl(private val db:IHMSDB) : IMessagingRepository {
    override suspend fun sendMessage(message: Message): Flow<Boolean> =flow {
        db.messages.add(message)
        emit(true)
    }

    override suspend fun getAllMessages(receiverId:String): Flow<List<Message>> = flow {
        val messages = db.messages.filter{messages -> messages.receiverId == receiverId}
        emit(messages)
    }

    override suspend fun getMessageById(id: String): Flow<Message> = flow{
        val message = db.messages.filter { message -> message.senderId==id }
        emit(message[0])
    }
}