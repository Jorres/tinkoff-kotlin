package ru.tinkoff.tarasov.testObjects

/**
 *  A kind of entity that is stored in database.
 *  @param x - with `data` keyword, allows comparison of [DefaultEntity] by value, not by reference.
 */
data class DefaultEntity(val x: Int = 0)

/**
 * An interface providing abstract methods for DB access.
 * Corresponds to p.5 of homework.
 */
interface DBAccess {
    fun searchById(id: Int): DefaultEntity?

    fun returnAllEntities(): List<DefaultEntity>
}