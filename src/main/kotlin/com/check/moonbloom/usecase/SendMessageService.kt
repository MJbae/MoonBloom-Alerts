package com.check.moonbloom.usecase

import com.check.moonbloom.model.MessageSender
import org.springframework.stereotype.Service

@Service
class SendMessageService(
    private val messageSender: MessageSender
) {
    // @Transactional(readonly=true)
    fun send() {
        // TODO: load all birthday which have before 1week from now.
        //  Load honoree and user too
        //  val msg = SendingMessage(honoree, user)
        //  Send msg with smsSender
    }
}