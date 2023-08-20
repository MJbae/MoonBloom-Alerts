package com.check.moonbloom.usecase

import com.check.moonbloom.config.MessageConfig
import com.check.moonbloom.infrastructure.NaverSigner
import com.check.moonbloom.model.*
import org.springframework.stereotype.Service

@Service
class NaverSmsV2Service(
    private val config: MessageConfig,
    private val msgSender: MessageClient
) {
    private val signer = NaverSigner()

    fun send(honoree: Honoree) {
        val msg = SmsMessage(honoree)
        val endpoint = "/sms/v2/services/${config.serviceId}/messages"
        val timestamp = (System.currentTimeMillis()).toString()
        val signature = signer.sign("POST", endpoint, timestamp, config.accessKey, config.secretKey)

        msgSender.send(
            timeStamp = timestamp,
            accessKey = config.accessKey,
            signature = signature,
            serviceId = config.serviceId,
            req = MsgRequest(
                type = "sms",
                from = config.phoneNo,
                content = msg.toString(),
                messages = listOf(
                    Message(
                        to = honoree.user.phoneNo,
                        subject = "MoonBloom Alerts 생신 알림",
                        content = msg.toString()
                    )
                )
            )
        )
    }
}