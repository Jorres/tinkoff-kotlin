package ru.tinkoff.tarasov.astro

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class CrewDao(private val database: Database) {
    fun getCrewById(id: Int): Optional<FlightCrew> {
        return transaction(database) {
            val res = CrewsTable.select{ CrewsTable.id eq id }
            assert(res.count() <= 1)
            if (res.count() == 1L) {
                Optional.of(extractCrew(res.first()))
            } else {
                Optional.empty()
            }
        }
    }

    fun deleteCrewById(id: Int) {
        transaction(database) {
            CrewsTable.deleteWhere { CrewsTable.id eq id }
        }
    }

    fun updateCrewById(id: Int, flightCrew: FlightCrew) {
        transaction(database) {
            CrewsTable.update ({ CrewsTable.id eq id }) {
                it[crewname] = flightCrew.flightName
            }
        }
    }

    fun createCrew(flightCrew: FlightCrew) {
        transaction(database) {
            CrewsTable.insert {
                it[crewname] = flightCrew.flightName
            }
        }
    }

    private fun extractCrew(row: ResultRow): FlightCrew = FlightCrew (
        row[CrewsTable.crewname]
    )
}