package ru.tinkoff.tarasov.astro

import java.util.*

class AstroService(private val astroDao: AstroDao, private val crewDao: CrewDao) {
    fun getAstronaut(id: Int): Optional<Astronaut> {
        return astroDao.getAstronautById(id)
    }

    fun deleteAstronaut(id: Int) {
        astroDao.deleteAstronautById(id)
    }

    fun updateAstronaut(id: Int, astronaut: Astronaut) {
        astroDao.updateAstronautById(id, astronaut)
    }

    fun createAstronaut(astronaut: Astronaut) {
        astroDao.createAstronaut(astronaut)
    }

    fun assignToCrew(assignRequest: AssignRequest) {
        val maybeCrew = crewDao.getCrewById(assignRequest.crewId)
        if (maybeCrew.isPresent) {
            astroDao.assignIdToCrewName(assignRequest.astronautId, maybeCrew.get().flightName)
        }
    }
}