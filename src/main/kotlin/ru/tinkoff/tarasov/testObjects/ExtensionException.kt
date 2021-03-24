package ru.tinkoff.tarasov.testObjects

/**
 *  Extension function to [SimpleClass], corresponds to p.2 of homework.
 */
fun SimpleClass.calculateWithValidation(srcPrice: Int, rate: Double, years: Double): Double {
    require(rate >= 0) { "Inflation rate is below zero" }
    return this.calculateInflationPrice(srcPrice, rate, years)
}