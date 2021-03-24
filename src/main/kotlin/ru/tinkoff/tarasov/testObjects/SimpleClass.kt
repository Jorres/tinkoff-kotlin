package ru.tinkoff.tarasov.testObjects

import kotlin.math.pow

/**
 * A simple class with a couple of methods to test.
 * Corresponds to p.1 of homework.
 */
class SimpleClass {
    fun isDataWritable(remaining: Int, required: Int): Boolean = remaining >= required

    fun calculateInflationPrice(srcPrice: Int, inflationRate: Double, years: Double): Double =
        srcPrice * (1 + inflationRate / 100.0).pow(years)
}