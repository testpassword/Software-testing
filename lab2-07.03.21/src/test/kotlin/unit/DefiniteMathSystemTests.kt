package unit

import math.DefiniteMathSystem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertAll
import kotlin.test.Test

class DefiniteMathSystemTests {

    val def = DefiniteMathSystem()

    @Test fun `DefiniteMathSystem class implementation test`() = assertAll(
        { Assertions.assertEquals(-5.9608, def(-1), 0.0001) },
        { Assertions.assertEquals(Double.NaN, def(0), 0.0001) },
        { Assertions.assertEquals(-0.0133, def(0.668), 0.0001) },
        { Assertions.assertEquals(0.0, def(1), 0.0001) },
        { Assertions.assertEquals(-0.0409, def(2.47), 0.0001) },
    )
}