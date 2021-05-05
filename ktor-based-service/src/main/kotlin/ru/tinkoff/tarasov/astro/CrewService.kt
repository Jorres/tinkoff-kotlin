package ru.tinkoff.tarasov.astro

import java.util.*

class CrewService(private val astroDao: AstroDao, private val crewDao: CrewDao) {
    fun getCrew(id: Int): Optional<FlightCrew> {
        return crewDao.getCrewById(id)
    }

    fun deleteCrew(id: Int) {
        crewDao.deleteCrewById(id)
    }

    fun updateCrew(id: Int, flightCrew: FlightCrew) {
        crewDao.updateCrewById(id, flightCrew)
    }

    fun createCrew(flightCrew: FlightCrew) {
        crewDao.createCrew(flightCrew)
    }

    fun listAssignedToCrew(id: Int): List<Astronaut> {
        val maybeCrew = crewDao.getCrewById(id)
        return if (maybeCrew.isPresent) {
            astroDao.selectAstronautsWithFlightName(maybeCrew.get().flightName)
        } else {
            listOf()
        }
    }
}