package integration

import math.*
import org.mockito.Mockito
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class TrigonometricTests {

    val cosMock = Mockito.mock(Cos::class.java)
    val sinMock = Mockito.mock(Sin::class.java)

    @Test fun `sin integration with cos test`() {
        val sin = Sin().apply { cos = cosMock }
        Mockito.`when`(cosMock(0.0)).thenReturn(1.0)
        assertEquals(-1.0, sin(-PI / 2))
    }

    @Test fun `cot integration with cos and sin test`() {
        val cot = Cot().apply {
            cos = cosMock
            sin = sinMock
        }
        Mockito.`when`(cosMock(PI / 2)).thenReturn(0.0)
        Mockito.`when`(sinMock(PI / 2)).thenReturn(1.0)
        assertEquals(0.0, cot(PI / 2))
    }

    @Test fun `sec integration with cos test`() {
        val sec = Sec().apply { cos = cosMock }
        Mockito.`when`(cosMock(PI)).thenReturn(-1.0)
        assertEquals(-1.0, sec(PI))
    }
}