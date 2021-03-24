package ru.tinkoff.tarasov.test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tinkoff.tarasov.testObjects.SimpleClass
import kotlin.math.pow

class SimpleClassTest {
    @Test
    fun `check when data is writable`() {
        val simpleClass = SimpleClass()
        val remaining = 200
        val required = 100

        assertEquals(true, simpleClass.isDataWritable(remaining, required))
    }

    @Test
    fun `check data is writable when required is exactly equal to remaining`() {
        val simpleClass = SimpleClass()
        val remaining = 100
        val required = 100

        assertEquals(true, simpleClass.isDataWritable(remaining, required))
    }

    @Test
    fun `check when data is not writable`() {
        val simpleClass = SimpleClass()
        val remaining = 100
        val required = 200

        assertEquals(false, simpleClass.isDataWritable(remaining, required))
    }

    @Test
    fun `inflation within zero years does not take place`() {
        val simpleClass = SimpleClass()
        val srcPrice = 100
        val inflationRate = 5.0
        val years = 0.0

        assertEquals(100.0, simpleClass.calculateInflationPrice(srcPrice, inflationRate, years))
    }

    @Test
    fun `inflation through one year is price * rate`() {
        val simpleClass = SimpleClass()
        val srcPrice = 100
        val inflationRate = 5.0
        val years = 1.0

        assertEquals(105.0, simpleClass.calculateInflationPrice(srcPrice, inflationRate, years))
    }

    @Test
    fun `inflation through ten years`() {
        val simpleClass = SimpleClass()
        val srcPrice = 100
        val inflationRate = 5.0
        val years = 10.0

        assertEquals(
            100.0 * (1.05).pow(years),
            simpleClass.calculateInflationPrice(srcPrice, inflationRate, years)
        )
    }
}