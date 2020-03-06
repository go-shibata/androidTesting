package com.example.go.androidtestingtest

import org.assertj.core.api.Assertions.*
import org.assertj.core.api.SoftAssertions
import org.junit.Test
import java.lang.RuntimeException

class AssertJTest {

    @Test
    fun test_Tokyo() {
        assertThat("TOKYO")
            .`as`("TEXT CHECK TOKYO")
            .isEqualTo("TOKYO")
            .isEqualToIgnoringCase("tokyo")
            .isNotEqualTo("KYOTO")
            .isNotBlank()
            .startsWith("TO")
            .endsWith("YO")
            .contains("OKY")
            .matches("[A-Z]{5}")
            .isInstanceOf(String::class.java)
    }

    @Test
    fun test_SoftAssertions_Tokyo() {
        SoftAssertions().apply {
            assertThat("TOKYO")
                .`as`("TEXT CHECK TOKYO")
                .isEqualTo("TOKYO")
                .isEqualToIgnoringCase("tokyo")
                .isNotEqualTo("KYOTO")
                .isNotBlank()
                .startsWith("TO")
                .endsWith("YO")
                .contains("OKY")
                .matches("[A-Z]{5}")
                .isInstanceOf(String::class.java)
        }.assertAll()
    }

    @Test
    fun test_Numeric() {
        assertThat(3.14159)
            .isNotZero()
            .isNotNegative()
            .isGreaterThan(3.0)
            .isLessThanOrEqualTo(4.0)
            .isBetween(3.0, 3.2)
            .isCloseTo(Math.PI, within(0.001))
    }

    @Test
    fun test_List() {
        val target = listOf("Giants", "Dodgers", "Athletics")

        assertThat(target)
            .hasSize(3)
            .contains("Dodgers")
            .containsOnly("Athletics", "Dodgers", "Giants")
            .containsExactly("Giants", "Dodgers", "Athletics")
            .doesNotContain("Padres")
    }

    @Test
    fun test_List_Filtering() {
        val target = listOf(
            BallTeam("Giants", "San Francisco", "AT&T Park"),
            BallTeam("Dodgers", "Los Angels", "Dodger Stadium"),
            BallTeam("Angels", "Los Angels", "Angel Stadium"),
            BallTeam("Athletics", "Oakland", "Oakland Coliseum"),
            BallTeam("Padres", "San Diego", "Petco Park")
        )

        assertThat(target)
            .filteredOn { team -> team.city.startsWith("San") }
            .filteredOn { team -> team.city.endsWith("Francisco") }
            .extracting("name", String::class.java)
            .containsExactly("Giants")

        assertThat(target)
            .filteredOn { team -> team.city == "Los Angels" }
            .extracting("name", "stadium")
            .containsExactly(
                tuple("Dodgers", "Dodger Stadium"),
                tuple("Angels", "Angel Stadium")
            )
    }

    @Test
    fun test_Exception() {
        val target = ExceptionThrower()
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { target.throwRuntimeException() }
            .withMessage("Aborted!")
            .withNoCause()
    }
}