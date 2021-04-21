package ru.tinkoff.tarasov

import java.sql.ResultSet

class AccessManager(val connManager: ConnectionManager) {

    fun selectEntryById(id: Int, tableName: String) {
        val query = "SELECT * FROM $tableName WHERE id = $id;"
        wrapWithResourseManagement(query, "name")
    }

    fun idGreaterThanTwo(tableName: String) {
        val query = "SELECT * FROM $tableName WHERE id > 2"
        wrapWithResourseManagement(query, "name")
    }

    fun selectWithJoin(
        srcTable: String, middleTable: String,
        srcField: String, middleField: String,
        targetTable: String, targetField: String
    ) {
        connManager.getConnection().use {
            val query = """
                SELECT * FROM $targetTable LEFT JOIN (
                    SELECT * 
                    FROM $srcTable LEFT JOIN $middleTable 
                    ON $srcTable.$srcField == $middleTable.$middleField
                ) as A ON $targetTable.id == A.$targetField;
            """.trimIndent()
            wrapWithResourseManagement(query, "name")
        }
    }

    fun selectGroupBy(tableName: String, columnName: String) {
        val query = "SELECT $columnName, COUNT(*) as cnt FROM $tableName GROUP BY $columnName;"
        wrapWithResourseManagement(query, "name", "cnt")
    }

    fun selectSortBy(tableName: String, columnName: String) {
        val query = "SELECT * FROM $tableName ORDER BY $columnName;"
        wrapWithResourseManagement(query, "name")
    }

    private fun printFields(r: ResultSet, vararg fields: String) {
        println("Request result: ")
        for (field in fields) {
            print("$field ")
        }
        println()

        while (r.next()) {
            for (field in fields) {
                print(r.getString(field) + " ")
            }
            println()
        }
        println()
    }

    private fun wrapWithResourseManagement(query: String, vararg fields: String) {
        connManager.getConnection().use { conn ->
            conn.createStatement().use { statement ->
                statement.executeQuery(query).use { resultSet ->
                    printFields(resultSet, *fields)
                }
            }
        }
    }
}