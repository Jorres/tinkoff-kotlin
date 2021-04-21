package ru.tinkoff.tarasov

import java.sql.SQLException

fun main() {
    val cm = ConnectionManager()
    val tm = TableManager(cm)

    try {
        tm.createTables()

        val am = AccessManager(cm)

        am.selectEntryById(2, "libraries")
        am.idGreaterThanTwo("books")
        am.selectSortBy("books", "pageCount")
        am.selectGroupBy("books", "name")
        am.selectWithJoin(
            "readers", "registrations",
            "id", "studentId",
            "libraries", "libraryId"
        )
    } catch (e: SQLException) {
        print("Top level exc: " + e.message)
    } finally {
        tm.safeDeleteTables()
    }
}