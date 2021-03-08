interface MathFunction<N : Number?> {

    val PRECISION: Double
        get() = 0.000001
    val MAX_ITERATIONS: Int
        get() = 1_000_000
    val STEP: Double
        get() = 1e-4

    val Number.isSpecial: Boolean
        get() = this in setOf(Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)

    operator fun invoke(vararg args: N, precision: Double = this.PRECISION): Double
}