package ru.tinkoff.tarasov.testObjects

/**
 * Data class to be instantiated in DSL style.
 * Corresponds to p.3 of homework.
 */
data class DSLStructure (
    val stringArg: String = "",
    val doubleArg: Double = 0.0,
    val log: (Double) -> String = { a -> stringArg + "$a" }
)

/**
 * A helper function to allow DSL-style instantiation of [DSLStructure].
 */
fun dslstructure(block: DSLStructure.() -> Unit): DSLStructure = DSLStructure().apply(block)

