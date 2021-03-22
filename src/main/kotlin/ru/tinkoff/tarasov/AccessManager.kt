package ru.tinkoff.tarasov

import java.sql.ResultSet

class AccessManager(val connManager: ConnectionManager) {
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

    fun selectEntryById(id: Int, tableName: String) {
        connManager.getConnection().use {
            printFields(
                it.createStatement().executeQuery(
                    """
                SELECT * FROM $tableName WHERE id = $id;            
            """.trimIndent()
                ),
                "name"
            )
        }
    }

    fun idGreaterThanTwo(tableName: String) {
        connManager.getConnection().use {
            printFields(
                it.createStatement().executeQuery(
                    """
                SELECT * FROM $tableName WHERE id > 2;            
            """.trimIndent()
                ),
                "name"
            )
        }
    }

    fun selectWithJoin(
        srcTable: String, middleTable: String,
        srcField: String, middleField: String,
        targetTable: String, targetField: String
    ) {
        connManager.getConnection().use {
            printFields(
                it.createStatement().executeQuery(
                    """
            SELECT * FROM $targetTable LEFT JOIN (
                SELECT * 
                FROM $srcTable LEFT JOIN $middleTable 
                ON $srcTable.$srcField == $middleTable.$middleField
            ) as A ON $targetTable.id == A.$targetField;
        """.trimIndent()
                ),
                "name"
            )
        }
    }

    fun selectGroupBy(tableName: String, columnName: String) {
        connManager.getConnection().use {
            printFields(
                it.createStatement().executeQuery(
                    """
                SELECT $columnName, COUNT(*) as cnt FROM $tableName GROUP BY $columnName;            
            """.trimIndent()
                ),
                "name", "cnt"
            )
        }
    }

    fun selectSortBy(tableName: String, columnName: String) {
        connManager.getConnection().use {
            printFields(
                it.createStatement().executeQuery(
                    """
                SELECT * FROM $tableName ORDER BY $columnName;            
            """.trimIndent()
                ),
                "name"
            )
        }
    }
}