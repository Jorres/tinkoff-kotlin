package ru.tinkoff.tarasov.test

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tinkoff.tarasov.testObjects.DBAccess
import ru.tinkoff.tarasov.testObjects.DefaultEntity

class DBAccessTest {
    @Test
    fun `check entity with small id is present`() {
        val dbaccess = mockk<DBAccess> {
            every { searchById(less(5, andEquals = true)) } returns DefaultEntity()
        }

        assertEquals(DefaultEntity(), dbaccess.searchById(3))

        verify {
            dbaccess.searchById(3)
        }
    }

    @Test
    fun `check entity with big enough id is null`() {
        val dbaccess = mockk<DBAccess> {
            every { searchById(more(5)) } returns null
        }

        assertEquals(null, dbaccess.searchById(10))

        verify {
            dbaccess.searchById(10)
        }
    }

    @Test
    fun `check access to all entities`() {
        val dbaccess = mockk<DBAccess> {
            every { returnAllEntities() } returns List(10) { DefaultEntity() }
        }

        assertEquals(List(10) { DefaultEntity() }, dbaccess.returnAllEntities())

        verify {
            dbaccess.returnAllEntities()
        }
    }
}