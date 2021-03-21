package integration

import math.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito
import kotlin.test.Test

// https://www.toptal.com/java/unit-integration-junit-tests
class LogarithmicTests {

    val lnMock = Mockito.mock(Ln::class.java)

    @Test fun `log integration with ln test`() {
        val log = Log().apply { ln = lnMock }
        Mockito.`when`(lnMock(0)).thenReturn(Double.NaN)
        Mockito.`when`(lnMock(10)).thenReturn(2.3025)
        assertEquals(Double.NaN, log(0, 10))
    }
}