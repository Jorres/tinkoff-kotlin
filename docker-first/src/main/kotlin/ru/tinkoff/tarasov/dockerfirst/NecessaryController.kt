package ru.tinkoff.tarasov.dockerfirst

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import java.sql.DriverManager
import java.time.Duration

/**
 * Controller that provides necessary data and communicates with PostgreSQL database.
 */
@RestController
class NecessaryController {
    private val dbUrl = System.getProperty("dbUrl")
    private val username = System.getProperty("username")
    private val password = System.getProperty("password")

    private val optionalRequestTimeoutMs: Long = 1000

    init {
        println("LOGGING ENV VARS")
        println("LOGGING ENV VARS")
        println("LOGGING ENV VARS")
        println("LOGGING ENV VARS")
        println("$dbUrl $username $password")
        println("LOGGING ENV VARS")
        println("LOGGING ENV VARS")
        println("LOGGING ENV VARS")
        println("LOGGING ENV VARS")
    }

    val baseUrl = "jdbc:postgresql://$dbUrl"
    val webClient = WebClient.create("http://optional-service:8080")

    @GetMapping("/necessary")
    fun supplyNecessary(): ResponseEntity<MainResponseObject> {
        val connection = DriverManager.getConnection(baseUrl, username, password)
        val statement = connection.createStatement()
        val rs = statement.executeQuery("SELECT * FROM necessary;")
        val resp = MainResponseObject()
        while (rs.next()) {
            resp.necessary = rs.getString("data")
        }

        try {
            val optionalData = webClient
                .get()
                .uri("/optional")
                .retrieve()
                .bodyToMono(OptionalObject::class.java)
                .timeout(Duration.ofMillis(optionalRequestTimeoutMs))
                .block()
                ?.data
            println("OPTIONAL DATA FROM OPTIONAL SERVER")
            println("OPTIONAL DATA FROM OPTIONAL SERVER")
            println("OPTIONAL DATA FROM OPTIONAL SERVER")
            println("OPTIONAL DATA FROM OPTIONAL SERVER")
            println(optionalData)
            println("OPTIONAL DATA FROM OPTIONAL SERVER")
            println("OPTIONAL DATA FROM OPTIONAL SERVER")
            println("OPTIONAL DATA FROM OPTIONAL SERVER")
            println("OPTIONAL DATA FROM OPTIONAL SERVER")
            resp.optional = optionalData
        } catch (e: Exception) {
            // TODO find what kind of exception expected on timeout
            // ignore it, optional field stays null
        }
        return ResponseEntity.ok(resp)
    }
}

data class MainResponseObject(var necessary: String? = null, var optional: String? = null)
data class OptionalObject(val data: String)
