import java.io.File
import java.io.FileOutputStream
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class TestResultCSVWriter(filepath: String = "") {

    private val file: File = File("${filepath}_${DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
        .withZone(ZoneOffset.UTC)
        .format(Instant.now())}")

    fun write(objects: Iterable<Any>) = FileOutputStream(file, true).bufferedWriter().use {
        it.write(objects.joinToString(separator = ",") { o -> o.toString() } + "\n")
    }
}