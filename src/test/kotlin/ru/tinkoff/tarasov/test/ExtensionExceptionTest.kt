package ru.tinkoff.tarasov.test

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.tinkoff.tarasov.testObjects.SimpleClass
import ru.tinkoff.tarasov.testObjects.calculateWithValidation
import java.lang.IllegalArgumentException
import kotlin.math.pow

class ExtensionExceptionTest {
    @Test
    fun `check exception thrown on invalid argument`() {
        val simpleClass = SimpleClass()
        val price = 100
        val rate = -1.0
        val years = 1.0

        val exc = assertThrows<IllegalArgumentException> {
            simpleClass.calculateWithValidation(price, rate, years)
        }
        assertEquals("Inflation rate is below zero", exc.message)
    }

    @Test
    fun `check no exception thrown with valid argument`() {
        val simpleClass = SimpleClass()
        val price = 100
        val rate = 1.0
        val years = 1.0

        assertDoesNotThrow {
            assertEquals(
                price * rate.pow(years),
                simpleClass.calculateWithValidation(price, rate, years)
            )
        }
    }
}