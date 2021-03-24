package ru.tinkoff.tarasov.test

import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tinkoff.tarasov.testObjects.DSLStructure

class DSLStructureTest {
    @Test
    fun `DSLStructure mock fields`() {
        val d = mockk<DSLStructure> {
            every { stringArg } returns "string"
            every { doubleArg } returns 3.14
        }

        assertAll ("Check mocked dslstructure",
            { assertEquals("string", d.stringArg) },
            { assertEquals(3.14, d.doubleArg) }
        )
    }

    @Test
    fun `DSLStructure mock function call`() {
        val d = mockk<DSLStructure> {
            every { log(less(5.0)) } returns "1"
            every { log(5.0) } returns "2"
            every { log(more(5.0)) } returns "3"
        }

        assertEquals("1", d.log(3.0))
        assertEquals("2", d.log(5.0))
        assertEquals("3", d.log(7.0))
        verifySequence {
            d.log(3.0)
            d.log(5.0)
            d.log(7.0)
        }
    }
}