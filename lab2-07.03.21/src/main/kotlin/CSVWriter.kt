import math.MathFunction
import java.io.File
import java.io.FileOutputStream
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class CSVWriter(filename: String = "") {

    val file: File = File("${filename}_${DateTimeFormatter
        .ofPattern("yyyy-MM-dd_HH-mm-ss")
        .withZone(ZoneOffset.UTC)
        .format(Instant.now())}.csv")

    fun write(objects: Iterable<Any>) =
        FileOutputStream(file.apply { createNewFile() }, true).bufferedWriter().use {
            it.write(objects.joinToString(separator = "\n") { o -> o.toString() } + "\n")
        }

    val Number.ruLocale: String
        get() = this.toString().replace(".", ",")
}

fun CSVWriter.writeFuncSeries(func: MathFunction<Number>,
                              from: Double,
                              accuracy: Double = 0.001,
                              until: Double = from + accuracy,
                              extraArgs: Array<Double> = emptyArray()) =
    this.write(generateSequence(from) { it + accuracy }
        .takeWhile { it <= until }
        .map { "${it.ruLocale}; ${func(args = arrayOf(it, *extraArgs)).ruLocale}" }
        .toList())