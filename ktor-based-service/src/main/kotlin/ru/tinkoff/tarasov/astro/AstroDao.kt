package ru.tinkoff.tarasov.astro

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class AstroDao (private val database: Database) {
    fun getAstronautById(id: Int): Optional<Astronaut> {
        return transaction(database) {
            val res = AstronautsTable.select{ AstronautsTable.id eq id }
            val cnt = res.count()
            assert(cnt <= 1)

            if (cnt == 1L) {
                Optional.of(extractAstronaut(res.first()))
            } else {
                Optional.empty()
            }
        }
    }

    fun deleteAstronautById(id: Int) {
        transaction(database) {
           AstronautsTable.deleteWhere { AstronautsTable.id eq id }
        }
    }

    fun updateAstronautById(id: Int, astronaut: Astronaut) {
        transaction(database) {
            AstronautsTable.update ({ AstronautsTable.id eq id }) {
               it[name] = astronaut.name
               it[crewname] = astronaut.crewName
            }
        }
    }

    fun createAstronaut(astronaut: Astronaut) {
        transaction(database) {
            AstronautsTable.insert {
                it[name] = astronaut.name
                it[crewname] = astronaut.crewName
            }
        }
    }

    fun assignIdToCrewName(whom: Int, newCrewName: String) {
        transaction(database) {
            AstronautsTable.update ({ AstronautsTable.id eq whom }) {
                it[crewname] = newCrewName
            }
        }
    }

    fun selectAstronautsWithFlightName(flightCrewName: String): List<Astronaut> {
       return transaction(database) {
           AstronautsTable.select { AstronautsTable.crewname eq flightCrewName }
               .map(::extractAstronaut)
       }
    }

    private fun extractAstronaut(row: ResultRow): Astronaut = Astronaut (
        row[AstronautsTable.name],
        row[AstronautsTable.crewname]
    )
}