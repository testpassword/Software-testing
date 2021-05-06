package joke.lab3

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.RemoteWebDriver
import java.util.concurrent.TimeUnit

typealias Env = Pair<WebDriver, PageObject>

object DriverFactory {

    // https://www.browserstack.com/guide/run-selenium-tests-using-firefox-driver
    fun create(pageClass: Class<out PageObject>,
               driverType: Class<out RemoteWebDriver> = FirefoxDriver::class.java): Env {
        WebDriverManager.getInstance(driverType).setup()
        val driver = driverType.getConstructor().newInstance().apply {
            manage().window().maximize()
            manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS)
            manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS)
            manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS)
        }
        return Env(driver, pageClass.getConstructor(WebDriver::class.java).newInstance(driver))
    }

    val BROWSERS_CLASSES =
        listOf(
            arrayOf(FirefoxDriver::class.java),
            arrayOf(ChromeDriver::class.java)
        )
}

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

fun WebElement.setAttribute(executor: WebDriver, attrName: String, attrValue: String) =
    (executor as JavascriptExecutor).executeScript("arguments[0].setAttribute('$attrName', '$attrValue')", this)

fun WebElement.changeStyle(executor: WebDriver, styleName: String, styleValue: String) =
    this.setAttribute(executor,
        "style",
        this.getAttribute("style")
            .split("; ")
            .associate { it.split(": ").zipWithNext()[0] }
            .toMutableMap()
            .also { it.replace(styleName, styleValue) }
            .map { "${it.key}: ${it.value}" }
            .joinToString("; ")
    )