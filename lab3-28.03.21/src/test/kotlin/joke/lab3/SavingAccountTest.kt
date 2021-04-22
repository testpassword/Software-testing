package joke.lab3

import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.RemoteWebDriver
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By
import java.util.concurrent.TimeUnit

class SavingAccountTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: SavingAccountPage

    @Before override fun setup() {
        with(DriverFactory.create(SavingAccountPage::class.java, browserType)) {
            driver = first
            page = second as SavingAccountPage
        }
        driver.get(page.url)
    }

    @Test fun `configure saving account`() {
        val oldAmount = page.amountOfResults.text
        page.moneyInput.sendKeys("10000000")
        page.configureBtn.click()
        // because without wait the element does not have time to change, and it does not have a waiting method without a condition
        TimeUnit.SECONDS.sleep(1)
        assertNotEquals(oldAmount, page.amountOfResults.text)
    }

    @Test fun `show all savings`() {
        page.showAllSavingsBtn.click()
        assertTrue(driver.isExist(By.xpath("//h1[text() = 'Подбор вклада']")))
    }

    @Test fun `show params`() {

    }
}