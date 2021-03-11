import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class LogarithmicTests {

    val ln = Ln()
    val log = Log()

    @Test fun `ln(x) series test`() = assertAll(
        { assertEquals(Double.NaN, ln(Random.nextDouble(until = 0.0)), 0.0001) },
        { assertEquals(Double.NaN, ln(0), 0.0001) },
        { assertEquals(-0.6931, ln(0.5), 0.0001) },
        { assertEquals(0.0, ln(1)) },
        { assertEquals(0.40546, ln(1.5), 0.0001) },
    )

    @Test fun `log(x) by with base test (based on ln(x) implementation inside)`() = assertAll(
        { assertEquals(Double.NaN, log(Random.nextDouble(until = 0.0), 10), 0.0001) },
        { assertEquals(Double.NaN, log(0, 10), 0.0001) },
        { assertEquals(-0.3010, log(0.5, 10), 0.0001) },
        { assertEquals(0.0, log(1, 10)) },
        { assertEquals(0.1760, log(1.5, 10), 0.0001) },
        { assertThrows<IllegalArgumentException> { log(2) } }
    )
}