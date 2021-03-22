package ru.tinkoff.tarasov

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class ConnectionManager {
    enum class DBType { SQLITE }

    val dbType = DBType.SQLITE
    val dbPath = "./src/main/resources/libraryDatabase"

    fun getConnection(): Connection {
        return when (dbType) {
            DBType.SQLITE -> DriverManager.getConnection("jdbc:sqlite:$dbPath")
        }
    }

    fun executeQuery(query: String): ResultSet {
        val connection = when (dbType) {
            DBType.SQLITE -> DriverManager.getConnection("jdbc:sqlite:$dbPath")
        }

        connection.use {
            return@executeQuery it.createStatement().executeQuery(query)
        }
    }

    fun execute(query: String): Boolean {
        val connection = when (dbType) {
            DBType.SQLITE -> DriverManager.getConnection("jdbc:sqlite:$dbPath")
        }
        connection.use {
            return@execute it.createStatement().execute(query)
        }
    }
}