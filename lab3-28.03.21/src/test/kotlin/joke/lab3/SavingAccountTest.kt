package joke.lab3

import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.RemoteWebDriver
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class SavingAccountTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: SavingAccountPage

    private fun getAmountOfSavings() = page.amountOfResults.text.split(" ")[0].toInt()

    @Before override fun setup() {
        with(DriverFactory.create(SavingAccountPage::class.java, browserType)) {
            driver = first
            page = second as SavingAccountPage
        }
        driver.get(page.url)
    }

    @Test fun `assort saving account`() {
        val oldAmount = getAmountOfSavings()
        page.moneyInput.sendKeys("10000000")
        page.configureBtn.click()
        // without sleep animation does not have time to play, and field in amountOfResults didn't changed
        TimeUnit.SECONDS.sleep(1)
        assertNotEquals(oldAmount, getAmountOfSavings())
    }

    @Test fun `show all savings`() {
        val oldAmount = getAmountOfSavings()
        page.showAllSavingsBtn.click()
        assertTrue(getAmountOfSavings() >= oldAmount)
    }
}