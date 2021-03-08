import kotlin.math.absoluteValue
import kotlin.math.pow

//https://www.cyberforum.ru/cpp-beginners/thread189032.html
class Sin: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double {
        val x = args[0].toDouble()
        return if (x.isSpecial) x else generateSequence(x to 1) {
            it.first * -x.pow(2) / ((2 * it.second) * (2 * it.second + 1)) to it.second + 1
        }.takeWhile { it.first.absoluteValue > PRECISION }.sumByDouble { it.first }
    }
}

class Cos: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            val sin = Sin()
            return if (it.isSpecial) it else sin(2 * it) / (2 * sin(it))
        }
}

class Cot: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let { if (it.isSpecial) it else Cos()(it) / Sin()(it) }
}

class Sec: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let { if (it.isSpecial) it else 1 / Cos()(it) }
}