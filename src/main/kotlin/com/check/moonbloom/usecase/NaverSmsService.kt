package com.check.moonbloom.usecase

import com.check.moonbloom.config.MessageConfig
import com.check.moonbloom.infrastructure.NaverSigner
import com.check.moonbloom.model.Honoree
import com.check.moonbloom.model.MsgRequest
import com.check.moonbloom.model.Message
import com.check.moonbloom.model.SmsMessage
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class NaverSmsService(
    private val config: MessageConfig,
    private val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val signer = NaverSigner()

    @Retryable(value = [Exception::class], backoff = Backoff(delay = 500), maxAttempts = 3)
    fun sendFor(honoree: Honoree) {
        val endpoint = "/sms/v2/services/${config.serviceId}/messages"
        val headers = createHeaders(endpoint)
        val request = createMsgRequest(honoree)
        val url = "${config.baseUrl}$endpoint"

        try {
            restTemplate.postForEntity(url, HttpEntity(request, headers), Void::class.java)
        } catch (ex: Exception) {
            logger.error("fail to send message, exception: ${ex.message}")
            throw ex
        }
    }

    private fun createHeaders(endpoint: String): HttpHeaders {
        val timestamp = (System.currentTimeMillis()).toString()
        val signature = signer.sign("POST", endpoint, timestamp, config.accessKey, config.secretKey)

        return HttpHeaders().apply {
            this["x-ncp-apigw-timestamp"] = timestamp
            this["x-ncp-iam-access-key"] = config.accessKey
            this["x-ncp-apigw-signature-v2"] = signature
        }
    }

    private fun createMsgRequest(honoree: Honoree): MsgRequest {
        val msg = SmsMessage(honoree)

        return MsgRequest(
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
    }
}
