import kotlin.math.pow

class DoubleMathSystem(private val equations: Pair<MathFunction<Number>, MathFunction<Number>>): MathFunction<Number> {

    override fun invoke(vararg args: Number, precision: Double): Double =
        if (args[0].toDouble() <= 0) equations.first(args[0]) else equations.second(args[0])
}

fun main() =
    println(DoubleMathSystem(
        object: MathFunction<Number> {
            override fun invoke(vararg args: Number, precision: Double): Double {
                val sec = Sec()
                val cos = Cos()
                val cot = Cot()
                val x = args[0].toDouble()
                return (sec(x) + cos(x)) * (cot(x) - sec(x))
            }} to
                object: MathFunction<Number> {
                    override fun invoke(vararg args: Number, precision: Double): Double {
                        val ln = Ln()
                        val log = Log()
                        val x = args[0].toDouble()
                        return (((log(x, 5).pow(2).pow(3)) - ln(x)) + log(x, 3)) * log(x, 3)
                    }}
    )(2.0))