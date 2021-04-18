package joke.lab3

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.runners.Parameterized
import org.openqa.selenium.WebDriver
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
        }
        return Env(driver, pageClass.getConstructor(WebDriver::class.java).newInstance(driver))
    }

    val BROWSERS_CLASSES =
        listOf(
            arrayOf(FirefoxDriver::class.java),
            arrayOf(ChromeDriver::class.java)
        )
}