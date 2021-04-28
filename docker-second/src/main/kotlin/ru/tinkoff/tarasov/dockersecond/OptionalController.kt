package ru.tinkoff.tarasov.dockersecond

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.DriverManager

/**
 * Controller that provides optional data and communicates with MySQL database.
 */
@RestController
class OptionalController {
    private val dbUrl = System.getProperty("dbUrl")
    private val username = System.getProperty("username")
    private val password = System.getProperty("password")

    val baseUrl = "jdbc:mysql://$dbUrl"

    @GetMapping("/optional")
    fun supplyOptional(): ResponseEntity<OptionalObject> {
        val connection = DriverManager.getConnection(baseUrl, username, password)
        val statement = connection.createStatement()
        val rs = statement.executeQuery("SELECT * FROM optional;")
        var data: String? = null
        while (rs.next()) {
            data = rs.getString("data")
            break
        }
        return ResponseEntity.ok(OptionalObject(data))
    }
}

data class OptionalObject(var data: String? = null)