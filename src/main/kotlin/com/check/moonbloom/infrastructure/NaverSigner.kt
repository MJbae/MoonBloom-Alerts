package com.check.moonbloom.infrastructure

import org.apache.tomcat.util.codec.binary.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class NaverSigner {
    fun sign(method: String, url: String, timestamp: String, accessKey: String, secretKey: String): String {
        val space = " " // one space
        val newLine = "\n" // new line
        val message = StringBuilder()
            .append(method)
            .append(space)
            .append(url)
            .append(newLine)
            .append(timestamp)
            .append(newLine)
            .append(accessKey)
            .toString()

        val signingKey = SecretKeySpec(secretKey.toByteArray(charset("UTF-8")), "HmacSHA256")
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(signingKey)
        val rawHmac = mac.doFinal(message.toByteArray(charset("UTF-8")))

        return Base64.encodeBase64String(rawHmac)
    }
}
