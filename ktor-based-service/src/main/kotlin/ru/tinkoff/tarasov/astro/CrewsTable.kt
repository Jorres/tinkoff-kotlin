package ru.tinkoff.tarasov.astro

import org.jetbrains.exposed.dao.id.IntIdTable

object CrewsTable : IntIdTable() {
    val crewname = text("crewname")
}
