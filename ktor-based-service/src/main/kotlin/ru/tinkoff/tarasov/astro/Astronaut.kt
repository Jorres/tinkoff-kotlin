package ru.tinkoff.tarasov.astro

import kotlinx.serialization.Serializable

@Serializable
data class Astronaut(val name: String, val crewName: String)