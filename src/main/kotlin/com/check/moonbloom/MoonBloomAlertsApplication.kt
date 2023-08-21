package com.check.moonbloom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class MoonBloomAlertsApplication

fun main(args: Array<String>) {
    runApplication<MoonBloomAlertsApplication>(*args)
}
