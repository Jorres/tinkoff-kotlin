package ru.tinkoff.tarasov.astro

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton
import java.lang.NullPointerException
import java.lang.NumberFormatException
import java.util.*


fun Application.astroModule() {
    val astroService: AstroService by closestDI().instance()
    val crewService: CrewService by closestDI().instance()

    // I'm not sure how to do it better. There must be a way to do it better :(
    // I tried to search for `ktor validate path variables`, but to no avail. Had to
    // do my own crutch. Please tell me how to do it normally))
    suspend fun validateId(call: ApplicationCall, processor: suspend (Int) -> Unit) {
        try {
            val id: Int = call.parameters["id"]!!.toInt()
            processor(id)
        // it is fun that Kotlin still does not have multi-exception catch block :)
        // https://youtrack.jetbrains.com/issue/KT-7128
        } catch (e: NumberFormatException) {
            call.respond(HttpStatusCode.BadRequest)
        } catch (e: NullPointerException) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    routing {
        route("astronaut") {
            get("/{id}") {
                validateId(call) { id ->
                    val maybeAstronaut = astroService.getAstronaut(id)
                    if (maybeAstronaut.isPresent) {
                        call.respond(maybeAstronaut.get())
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }

            post("/new") {
                val astronaut = call.receive<Astronaut>()
                astroService.createAstronaut(astronaut)
            }

            put("/{id}") {
                val astronaut = call.receive<Astronaut>()
                validateId(call) { id ->
                    call.respond(astroService.updateAstronaut(id, astronaut))
                }
            }

            delete("/{id}") {
                validateId(call) { id ->
                    astroService.deleteAstronaut(id)
                    call.respond(HttpStatusCode.OK)
                }
            }

            post("/assign/") {
                val assignRequest = call.receive<AssignRequest>()
                astroService.assignToCrew(assignRequest)
            }
        }

        route("crew") {
            get("/{id}") {
                validateId(call) { id ->
                    val maybeCrew = crewService.getCrew(id)
                    if (maybeCrew.isPresent) {
                        call.respond(maybeCrew.get())
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }

            post("/new") {
                val crew = call.receive<FlightCrew>()
                crewService.createCrew(crew)
            }

            put("/{id}") {
                val crew = call.receive<FlightCrew>()
                validateId(call) { id ->
                    call.respond(crewService.updateCrew(id, crew))
                }
            }

            delete("/{id}") {
                validateId(call) { id ->
                    call.respond(crewService.deleteCrew(id))
                }
            }

            get("/list/{id}") {
                validateId(call) { id ->
                    call.respond(crewService.listAssignedToCrew(id))
                }
            }
        }
    }
}

data class AssignRequest(val astronautId: Int, val crewId: Int)

fun DI.Builder.astroComponents() {
    bind<AstroDao>() with singleton {
        AstroDao(instance())
    }

    bind<CrewDao>() with singleton {
        CrewDao(instance())
    }

    bind<AstroService>() with singleton {
        AstroService(instance(), instance())
    }

    bind<CrewService>() with singleton {
        CrewService(instance(), instance())
    }
}
