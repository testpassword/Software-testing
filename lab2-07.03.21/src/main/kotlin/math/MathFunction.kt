package math

import kotlin.math.absoluteValue

interface MathFunction<N: Number?> {

    companion object {
        const val PRECISION = 0.000000001
        const val MAX_ITERATIONS = 1_000_000
    }

    val Number.isSpecial: Boolean
        get() = this in setOf(Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)

    fun Double.normalize(accuracy: Double) = if (this.absoluteValue - 0.0 < accuracy) 0.0 else this

    operator fun invoke(vararg args: N, precision: Double = PRECISION): Double
}