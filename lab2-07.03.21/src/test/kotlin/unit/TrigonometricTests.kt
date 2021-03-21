package unit

import CSVWriter
import math.*
import org.junit.jupiter.api.assertAll
import kotlin.math.PI
import kotlin.test.Test
import org.junit.jupiter.api.Assertions.assertEquals
import writeFuncSeries

class TrigonometricTests {

    val cos = Cos()
    val sin = Sin()
    val cot = Cot()
    val sec = Sec()

    @Test fun `cos(x) Taylor series test (root function for all other trigonometric functions`() = assertAll(
        { assertEquals(-1.0, cos(-PI), 0.0001) },
        { assertEquals(0.0, cos(-PI / 2), 0.0001) },
        { assertEquals(0.7316, cos(-0.75), 0.0001) },
        { assertEquals(1.0, cos(0), 0.0001) },
        { assertEquals(0.0, cos(PI / 2), 0.0001) },
        { assertEquals(0.3623, cos(1.2), 0.0001) },
        { assertEquals(-1.0, cos(PI), 0.0001) }
    )

    @Test fun `Manual test cos(from -1 to 1) series`() =
        CSVWriter("reports/cos").writeFuncSeries(cos, -PI, 0.1, PI)

    @Test fun `sin(x) test (based on cos(x) implementation inside)`() = assertAll(
        { assertEquals(0.0, sin(-PI), 0.0001) },
        { assertEquals(-1.0, sin(-PI / 2), 0.0001) },
        { assertEquals(-0.6816, sin(-0.75), 0.0001) },
        { assertEquals(0.0, sin(0), 0.0001) },
        { assertEquals(1.0, sin(PI / 2), 0.0001) },
        { assertEquals(0.9320, sin(1.2), 0.0001) },
        { assertEquals(0.0, sin(PI), 0.0001) }
    )

    @Test fun `Manual test sin(from -1 to 1) series`() =
        CSVWriter("reports/sin").writeFuncSeries(sin, -PI, 0.1, PI)

    @Test fun `cot(x) test (based on cos(x) and sin(x) implementation inside)`() = assertAll(
        { assertEquals(0.0, cot(-PI / 2), 0.0001) },
        { assertEquals(-1.0734, cot(-0.75), 0.0001) },
        { assertEquals(Double.NaN, cot(0), 0.0001) },
        { assertEquals(0.0, cot(PI / 2), 0.0001) },
        { assertEquals(0.3887, cot(1.2), 0.0001) }
    )

    @Test fun `Manual test cot(from -1 to 1) series`() =
        CSVWriter("reports/cot").writeFuncSeries(cot, -PI, 0.1, PI)

    @Test fun `sec(x) test (based on cos(x) implementation inside)`() = assertAll(
        { assertEquals(-1.0, sec(-PI), 0.0001) },
        { assertEquals(Double.POSITIVE_INFINITY, sec(-PI / 2), 0.0001) },
        { assertEquals(1.1105, sec(-0.45), 0.0001) },
        { assertEquals(1.0, sec(0), 0.0001) },
        { assertEquals(Double.POSITIVE_INFINITY, sec(PI / 2), 0.0001) },
        { assertEquals(1.1658, sec(0.54), 0.0001) },
        { assertEquals(-1.0, sec(PI), 0.0001) }
    )

    @Test fun `Manual test sec(from -1 to 1) series`() =
        CSVWriter("reports/sec").writeFuncSeries(sec, -PI, 0.1, PI)
}