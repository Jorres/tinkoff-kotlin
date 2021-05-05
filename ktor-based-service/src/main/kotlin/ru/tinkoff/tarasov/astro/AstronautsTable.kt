package ru.tinkoff.tarasov.astro

import org.jetbrains.exposed.dao.id.IntIdTable

object AstronautsTable : IntIdTable() {
    val name = text("name")
    val crewname = text("crewname")
}
