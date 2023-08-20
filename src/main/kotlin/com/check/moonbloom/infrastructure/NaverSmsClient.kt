package com.check.moonbloom.infrastructure

import com.check.moonbloom.model.MessageClient
import com.check.moonbloom.model.MsgRequest
import com.check.moonbloom.model.MsgResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.PostExchange

interface NaverSmsClient : MessageClient {

    @PostExchange("/sms/v2/services/{serviceId}/messages")
    override fun send(
        @RequestHeader(value = "x-ncp-apigw-timestamp") timeStamp: String,
        @RequestHeader(value = "x-ncp-iam-access-key") accessKey: String,
        @RequestHeader(value = "x-ncp-apigw-signature-v2") signature: String,
        @PathVariable(value = "serviceId") serviceId: String,
        @RequestBody req: MsgRequest
    ): MsgResponse
}

