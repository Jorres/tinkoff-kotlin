import java.sql.DriverManager
import java.sql.SQLException

// package
// custom exception
// 3 entry points could be run in any order
// multiple drivers
// initDB(address) -> connection

fun main() {
    try {
        val cm = ConnectionManager()
        val tm = TableManager(cm)
        tm.createTables()

        // val am = AccessManager(cm)
    } catch (e: SQLException) {
        print("Outer level exc: " + e.message)
    }
}

fun test() {
    try {
        val conn = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/clientDatabase")
            .use {
                val del = it.createStatement().execute(
                    """
                        DROP TABLE client;
                    """.trimIndent()
                )

                val res = it.createStatement().execute(
                    """
                    CREATE TABLE client (
                        id INTEGER PRIMARY_KEY,
                        name TEXT
                    );
                """.trimIndent()
                )

                val res2 = it.createStatement().execute(
                    """
                    INSERT INTO client (id, name) VALUES (1, "Egor");
                """.trimIndent()
                )
            }
    } catch (e : SQLException) {
        print(e.message)
    }
}