package joke.lab3

import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver

@RunWith(Parameterized::class) abstract class AbstractPageTest(val browserType: Class<RemoteWebDriver>) {

    lateinit var driver: WebDriver

    companion object { @JvmStatic @Parameterized.Parameters(name = "{0}") fun params() = DriverFactory.BROWSERS_CLASSES }

    @Before abstract fun setup()

    @After fun teardown() = driver.quit()
}