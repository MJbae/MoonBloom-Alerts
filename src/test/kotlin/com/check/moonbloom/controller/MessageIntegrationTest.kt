package com.check.moonbloom.controller

import com.check.moonbloom.TestFactory
import com.check.moonbloom.model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Profile("test")
class MessageIntegrationTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var honoreeRepository: HonoreeRepository
    private lateinit var req: NotificationTestRequest

    @BeforeEach
    fun setup() {
        honoreeRepository.deleteAll()
        userRepository.deleteAll()

        req = NotificationTestRequest(
            calendarType = CalendarType.LUNAR,
            phoneNo = TestFactory.randomPhoneNo(),
            dob = TestFactory.randomDate(),
            relationship = Relationship.values().random(),
            name = TestFactory.randomName()
        )
    }


    @Test
    fun `생년월일로_요청_시_음력_생일을_양력으로_변환한다`() {
        val res = webTestClient.post()
            .uri("/api/notifications")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(req)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(MessageTestDto::class.java)
            .returnResult()
            .responseBody!!

        assertEquals(res.honoree.gregorianBirthday, Birthday(req.dob, CalendarType.LUNAR).gregorian())
    }
}

data class NotificationTestRequest(
    val calendarType: CalendarType?,
    val phoneNo: String,
    val dob: LocalDate,
    val relationship: Relationship,
    val name: String
)

data class MessageTestDto(
    val honoree: HonoreeTestDto,
    val phoneNo: String
)

data class HonoreeTestDto(
    val relationship: String,
    val gregorianBirthday: LocalDate
)