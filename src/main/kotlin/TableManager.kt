class TableManager(val manager: ConnectionManager) {
    val tableInfo: List<Pair<String, List<String>>> = listOf(
        "books" to listOf(
            "id INTEGER",
            "name TEXT",
            "pageCount INTEGER"
        ),
        "libraries" to listOf(
            "id INTEGER",
            "name TEXT"
        ),
        "readers" to listOf(
            "id INTEGER",
            "name TEXT"
        ),
        "registrations" to listOf(
            "id INTEGER",
            "studentID INTEGER",
            "libraryID INTEGER"
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
            Book(1, "Book A", 100),
            Book(2, "Book B", 200),
            Book(3, "Book C", 300),
            Book(4, "Book D", 400),
        )

        val readers = listOf(
            Reader(1, "Egor", listOf(1, 2)),
            Reader(2, "Kolya", listOf(3, 4))
        )

        val libraries = listOf(
            Library(1, "Library A", listOf(1)),
            Library(2, "Library B", listOf(1, 2))
        )

        books.forEach { (id, name, pageCount) ->
            manager.execute("""
                INSERT INTO books (id, name, pageCount) 
                VALUES ($id, "$name", $pageCount);
            """.trimIndent()
            )
        }

        libraries.forEach { (id, name) ->
            manager.execute("""
                INSERT INTO libraries (id, name)
                VALUES ($id, "$name");
            """.trimIndent()
            )
        }

        readers.forEach { (id, name) ->
            manager.execute("""
                INSERT INTO readers (id, name)
                VALUES ($id, "$name");
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