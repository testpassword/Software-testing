import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.*
import kotlin.math.PI
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FunctionsTests {

    @BeforeAll fun init() = println("Init functions module test")

    @AfterAll fun ended() = println("All test are ended")

    private fun <T: Comparable<T>> List<T>.bubbleSort(): List<T> =
        this.toMutableList().apply {
            for (i in 0..this.size - 2)
                for (j in 0..this.size - i - 2)
                    if (this[j] > this[j + 1])
                        this[j] = this[j + 1].also { this[j + 1] = this[j] } }

    @Test fun `QuickSort algorithm test`() = assertAll(
        { List(50) { Random.nextInt() }.let { assertEquals(it.bubbleSort(), it.quickSort(), "😭") } },
        { assertEquals(listOf(-2, -2, 5, 7, 10, 15, 23 ,45), listOf(10, 23, 45, 15, 5, -2, -2, 7).quickSort()) }
    )

    @Test fun `arcsin(x) algorithm test`() = assertAll(
        { assertEquals(Double.NaN, arcsin(-1.1), 0.001) },
        { assertEquals(-PI / 2, arcsin(-1), 0.001) },
        { assertEquals(-0.5235987755982989, arcsin(-0.5), 0.001) },
        { assertEquals(0.0, arcsin(0), 0.001) },
        { assertEquals(0.5235987755982989, arcsin(0.5), 0.001) },
        { assertEquals(PI / 2, arcsin(1), 0.001) },
        { assertEquals(Double.NaN, arcsin(1.1), 0.001) })
}