import kotlin.math.*

/**
 * Функция `arcsin(x)`
 */
fun arcsin(x: Number): Double =
    x.toDouble().let {
        if (abs(it) <= 1) {
            val fact: (Int) -> Int = { (1..it).fold(1) { acc, i -> acc * i } }
            generateSequence(0) { n -> n + 1 }
                .take(33)
                .map { n -> (fact(2 * n) / (4.0.pow(n) * fact(n).toDouble().pow(2) * (2 * n + 1))) * it.pow(2 * n + 1) }
                .sum()
        } else Double.NaN }

/**
 * @see <a href="http://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html">Программный модуль для сортировки массива по алгоритму быстрой сортировки</a>
 */
fun <T: Comparable<T>> List<T>.quickSort(): List<T> =
    if (size >= 2) {
        val pivot = first()
        val (smaller, greater) = drop(1).partition { it <= pivot }
        smaller.quickSort() + pivot + greater.quickSort()
    } else this