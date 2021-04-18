package joke.lab3

import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

val String.numeric: Double?
    get() = this.replace(",", ".").replace(" ", "").toDoubleOrNull()

operator fun WebElement.get(attribute: String) = this.getAttribute(attribute)

infix fun WebDriver.isExist(by: By) =
    try {
        this.findElement(by)
        true
    } catch (e: NoSuchElementException) {
        false
    }