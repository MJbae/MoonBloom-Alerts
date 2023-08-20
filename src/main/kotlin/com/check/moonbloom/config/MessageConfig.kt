package com.check.moonbloom.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class MessageConfig(
    @Value("\${sms.baseUrl}")
    val baseUrl: String,
    @Value("\${sms.serviceId}")
    val serviceId: String,
    @Value("\${sms.key.access}")
    val accessKey: String,
    @Value("\${sms.key.secret}")
    val secretKey: String,
    @Value("\${sms.phoneNo}")
    val phoneNo: String,
)
