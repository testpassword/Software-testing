package joke.lab3

import org.junit.Before
import org.openqa.selenium.remote.RemoteWebDriver

class BankRateTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: BankRatePage

    @Before override fun setup() {
        with(DriverFactory.create(BankRatePage::class.java, browserType)) {
            driver = first
            page = second as BankRatePage
        }
        driver.get(page.url)
    }
}