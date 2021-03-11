import kotlin.math.*

// https://www.cyberforum.ru/cpp-beginners/thread189032.html
// Я ошибся вариантом и взял неправильную "корневую" функцию. Удалять было жалко, поэтому переименовал в Original
@Deprecated("use function were sin is working on cos")
class SinOriginal: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double {
        val x = args[0].toDouble()
        return if (x.isSpecial) x else generateSequence(x to 1) {
            it.first * -x.pow(2) / ((2 * it.second) * (2 * it.second + 1)) to it.second + 1
        }.takeWhile { it.first.absoluteValue > precision }.sumByDouble { it.first }
    }
}

@Deprecated("use function were cos is root operation")
class CosOriginal: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            val sin = SinOriginal()
            return if (it.isSpecial) it else sin(2 * it, precision = precision) / (2 * sin(it, precision = precision))
        }
}

class Sin: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            val cos = Cos()
            return if (it.isSpecial) it else -cos(it + PI / 2, precision = precision).normalize(precision)
        }
}

class Cos: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double {
        val x = args[0].toDouble()
        return if (x.isSpecial) x else
            generateSequence(1.0 to 1) {
                it.first * -x.pow(2) / ((2 * it.second - 1) * (2 * it.second)) to it.second + 1
            }.takeWhile { it.first.absoluteValue > precision }.sumByDouble { it.first }.normalize(precision)
    }
}

class Cot: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let {
            when {
                it.isSpecial -> it
                it == 0.0 -> Double.NaN
                else -> Cos()(it, precision = precision) / Sin()(it, precision = precision)
            }
        }
}

class Sec: MathFunction<Number> {
    override fun invoke(vararg args: Number, precision: Double): Double =
        args[0].toDouble().let { if (it.isSpecial) it else 1 / Cos()(it, precision = precision) }
}

fun main() {
    println(Sec()(PI / 2))
}