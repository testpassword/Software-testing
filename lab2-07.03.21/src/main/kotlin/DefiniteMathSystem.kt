import kotlin.math.pow

class DefiniteMathSystem: MathFunction<Number> {

    private val sec = Sec()
    private val cos = Cos()
    private val cot = Cot()
    private val ln = Ln()
    private val log = Log()

    override fun invoke(vararg args: Number, precision: Double): Double {
        val x = args[0].toDouble()
        val p = precision
        return if (args[0].toDouble() <= 0)
                (sec(x, precision = p) + cos(x, precision = p)) * (cot(x, precision = p) - sec(x, precision = p))
        else (((log(x, 5, precision = p).pow(2).pow(3)) - ln(x, precision = p)) + log(x, 3, precision = p)) * log(x, 3, precision = p)
    }
}