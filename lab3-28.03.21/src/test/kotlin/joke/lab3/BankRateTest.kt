package joke.lab3

import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.RemoteWebDriver
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By

class BankRateTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: BankRatePage

    @Before override fun setup() {
        with(DriverFactory.create(BankRatePage::class.java, browserType)) {
            driver = first
            page = second as BankRatePage
        }
        driver.get(page.url)
    }

    @Test fun `show year champ table`() {
        page.champRateBtn.click()
        assertTrue(driver isExist By.xpath("//span[contains(@class, 'active') and @data-test = 'main-tab']"))
    }

    @Test fun `show main table`() = assertNotNull(page.bankRateTbl)

    @Test fun `open review page`() {
        page.allReviewsBtn.click()
        assertTrue(driver.findElements(By.xpath("//article[@class = 'responses__item' and @data-test = 'responses-item']")).isNotEmpty())
    }

    @Test fun `find in table`() {
        val bankName = "Альфа-Банк"
        page.tableSearchInput.sendKeys(bankName)
        page.searchCandidatesList.changeStyle(driver, "display", "visible")
        driver.findElement(By.xpath("//div[@class = 'tt-suggestion tt-selectable']")).click()
        assertEquals(driver.findElement(By.xpath("//tr[contains(@class, 'selected-item')]//a")).text, bankName)
    }
}