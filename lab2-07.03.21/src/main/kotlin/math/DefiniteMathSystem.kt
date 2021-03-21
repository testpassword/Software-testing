package math

import kotlin.math.pow

class DefiniteMathSystem: MathFunction<Number> {

    var sec = Sec()
    var cos = Cos()
    var cot = Cot()
    var ln = Ln()
    var log = Log()

    override fun invoke(vararg args: Number, precision: Double): Double {
        val x = args[0].toDouble()
        val p = precision
        return if (args[0].toDouble() <= 0)
                (sec(x, precision = p) + cos(x, precision = p)) * (cot(x, precision = p) - sec(x, precision = p))
        else (((log(x, 5, precision = p).pow(2).pow(3)) - ln(x, precision = p)) + log(x, 3, precision = p)) * log(x, 3, precision = p)
    }
}