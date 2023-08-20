package com.check.moonbloom.usecase

import com.check.moonbloom.config.MessageConfig
import com.check.moonbloom.infrastructure.NaverSigner
import com.check.moonbloom.model.Honoree
import com.check.moonbloom.model.MsgRequest
import com.check.moonbloom.model.Message
import com.check.moonbloom.model.SmsMessage
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class NaverSmsService(
    private val config: MessageConfig,
    private val restTemplate: RestTemplate
) {
    private val signer = NaverSigner()

    fun send(honoree: Honoree) {
        val msg = SmsMessage(honoree)
        val endpoint = "/sms/v2/services/${config.serviceId}/messages"
        val timestamp = (System.currentTimeMillis()).toString()
        val signature = signer.sign("POST", endpoint, timestamp, config.accessKey, config.secretKey)

        val headers = HttpHeaders().apply {
            this["x-ncp-apigw-timestamp"] = timestamp
            this["x-ncp-iam-access-key"] = config.accessKey
            this["x-ncp-apigw-signature-v2"] = signature
        }

        val request = MsgRequest(
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

        val entity = HttpEntity(request, headers)
        val url = "${config.baseUrl}$endpoint"

        restTemplate.postForEntity(url, entity, Void::class.java)
    }
}
