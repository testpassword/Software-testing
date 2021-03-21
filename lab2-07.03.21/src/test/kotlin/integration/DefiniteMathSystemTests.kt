package integration

import math.*
import org.junit.jupiter.api.assertAll
import org.mockito.Mockito
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class DefiniteMathSystemTests {

    val cosMock = Mockito.mock(Cos::class.java)
    val cotMock = Mockito.mock(Cot::class.java)
    val secMock = Mockito.mock(Sec::class.java)
    val lnMock = Mockito.mock(Ln::class.java)
    val logMock = Mockito.mock(Log::class.java)

    @Test fun `definite math system integration with all required functions test`() {
        val def = DefiniteMathSystem().apply {
            cos = cosMock
            cot = cotMock
            sec = secMock
            ln = lnMock
            log = logMock
        }
        val t = - PI / 2
        Mockito.`when`(secMock(t)).thenReturn(Double.POSITIVE_INFINITY)
        Mockito.`when`(cosMock(t)).thenReturn(0.0)
        Mockito.`when`(cotMock(t)).thenReturn(0.0)
        Mockito.`when`(lnMock(1.0)).thenReturn(0.0)
        Mockito.`when`(logMock(1.0, 5.0)).thenReturn(0.0)
        Mockito.`when`(logMock(1.0, 3.0)).thenReturn(0.0)
        assertAll(
            { assertEquals(0.0, def(1)) },
            { assertEquals(Double.NEGATIVE_INFINITY, def(t)) }
        )
    }
}