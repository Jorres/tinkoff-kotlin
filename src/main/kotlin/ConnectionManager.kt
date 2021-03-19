import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class ConnectionManager {
    enum class DBType { SQLITE }

    val dbType = DBType.SQLITE
    val dbPath = "./src/main/resources/libraryDatabase"

    // TODO: learn to reuse connections
    fun executeQuery(query: String): ResultSet? {
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