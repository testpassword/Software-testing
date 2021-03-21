package math

import kotlin.math.absoluteValue
import kotlin.math.pow

class Ln: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double {
        val x = args[0].toDouble()
        return if (x <= 0 || x.isSpecial) Double.NaN else {
            val isAbsLessThen1 = (x - 1).absoluteValue <= 1
            val raw = generateSequence(0.0 to 1) {
                it.first - ((-1.0).pow(it.second) * (x - 1).pow(if (isAbsLessThen1) it.second else -it.second) / it.second) to it.second + 1
            }.takeWhile { precision <= (it.first - precision).absoluteValue && it.second < MathFunction.MAX_ITERATIONS }.toList().last().first
            if (isAbsLessThen1) raw else raw + invoke(x - 1)
        }
    }
}

class Log: MathFunction<Number> {

    var ln = Ln()

    override fun invoke(vararg args: Number, precision: Double): Double {
        if (args.size < 2) throw IllegalArgumentException("Two arguments are needed: parameter and base")
        val (x, base) = args.map { it.toDouble() }
        return if (x <= 0 || x.isSpecial || base.isSpecial) Double.NaN else
            ln(x, precision = precision) / ln(base, precision = precision)
    }
}