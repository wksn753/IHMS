package domain.model

import java.time.Instant

data class Message(var messageId: String="", var senderId: String="", var receiverId: String="",var message: String="",val timeStamp:Long = Instant.now().toEpochMilli())
