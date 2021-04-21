package ru.tinkoff.tarasov

class TableManager(val manager: ConnectionManager) {
    val tableInfo: List<Pair<String, List<String>>> = listOf(
        "books" to listOf(
            "id INTEGER PRIMARY KEY NOT NULL",
            "name TEXT",
            "pageCount INTEGER"
        ),
        "libraries" to listOf(
            "id INTEGER PRIMARY KEY NOT NULL",
            "name TEXT"
        ),
        "readers" to listOf(
            "id INTEGER PRIMARY KEY NOT NULL",
            "name TEXT"
        ),
        "registrations" to listOf(
            "id INTEGER PRIMARY KEY NOT NULL",
            "studentId INTEGER",
            "libraryId INTEGER"
        )
    )

    fun createTables() {
        safeDeleteTables()
        tableInfo.forEach { (name, columns) ->
            val columnsDesc = columns.joinToString(separator = ", ")
            manager.execute(
                """
                CREATE TABLE $name ($columnsDesc);
            """.trimIndent()
            )
        }
        populateTables()
    }

    private fun populateTables() {
        val books = listOf(
            Book("Book A", 100),
            Book("Book B", 300),
            Book("Book C", 200),
            Book("Book D", 400),
            Book("Book D", 400),
        )

        val readers = listOf(
            Reader("Egor", listOf(1, 2)),
            Reader("Kolya", listOf(3, 4))
        )

        val libraries = listOf(
            Library("Library A", listOf(1)),
            Library("Library B", listOf(1, 2))
        )

        val registrations = listOf(
            Registration(1, 2),
            Registration(2, 1),
            Registration(2, 2)
        )

        books.forEach { (name, pageCount) ->
            manager.execute("""
                INSERT INTO books (name, pageCount) 
                VALUES ("$name", $pageCount);
            """.trimIndent()
            )
        }

        libraries.forEach { (name) ->
            manager.execute("""
                INSERT INTO libraries (name)
                VALUES ("$name");
            """.trimIndent()
            )
        }

        readers.forEach { (name) ->
            manager.execute("""
                INSERT INTO readers (name)
                VALUES ("$name");
            """.trimIndent())
        }

        registrations.forEach { (studentId, libraryId) ->
            manager.execute("""
                INSERT INTO registrations (studentId, libraryId)
                VALUES ($studentId , $libraryId);
            """.trimIndent())
        }
    }

    fun safeDeleteTables() {
        tableInfo.forEach { (name, _) ->
            manager.execute(
                """
                DROP TABLE IF EXISTS $name;
            """.trimIndent()
            )
        }
    }
}