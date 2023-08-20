package com.check.moonbloom.config

import com.check.moonbloom.infrastructure.NaverSmsClient
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
class HttpClientConfig(
    @Value("\${sms.baseUrl}")
    val baseUrl: String,
) {
    @Bean
    fun webClient(): WebClient {
        val httpClient: HttpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)
            .responseTimeout(Duration.ofMillis(60000))
            .doOnConnected { conn ->
                conn.addHandlerLast(ReadTimeoutHandler(60000, TimeUnit.MILLISECONDS))
                    .addHandlerLast(WriteTimeoutHandler(60000, TimeUnit.MILLISECONDS))
            }

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .baseUrl(baseUrl)
            .build()
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }


    @Bean
    fun httpServiceProxyFactory(webClient: WebClient): HttpServiceProxyFactory {
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
    }

    @Bean
    fun naverSmsSender(httpServiceProxyFactory: HttpServiceProxyFactory): NaverSmsClient {
        return httpServiceProxyFactory.createClient(NaverSmsClient::class.java)
    }
}