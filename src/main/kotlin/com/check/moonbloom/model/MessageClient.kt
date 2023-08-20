package com.check.moonbloom.model


interface MessageClient {
    fun send(
        timeStamp: String,
        accessKey: String,
        signature: String,
        serviceId: String,
        req: MsgRequest
    ): MsgResponse
}


data class MsgRequest(
    val type: String,
    val from: String,
    val content: String,
    val messages: List<Message>
)

data class Message(
    val to: String,
    val subject: String,
    val content: String
)

data class MsgResponse(
    val requestId: String,
    val requestTime: String,
    val statusCode: String,
    val statusName: String
)