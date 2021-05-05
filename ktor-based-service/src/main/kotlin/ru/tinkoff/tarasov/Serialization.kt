package ru.tinkoff.tarasov

import io.ktor.serialization.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

// offloads a portion of serialization work to compile time
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
