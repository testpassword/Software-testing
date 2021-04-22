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
        assertTrue(driver.isExist(By.xpath("//span[contains(@class, 'active') and @data-test = 'main-tab']")))
    }

    @Test fun `show main table`() = assertNotNull(page.bankRateTbl)

    @Test fun `find in table`() {
        page.tableSearchInput.sendKeys("Альфа-Банк")
        page.searchCandidatesList.changeStyle(driver, "display", "visible")
        driver.findElement(By.xpath("//div[contains(@class, 'tt-selectable')]")).click()
        assertTrue(driver.isExist(By.xpath("//tr[contains(@class, 'selected-item')]/a[text() = 'Альфа-Банк']")))
    }

    @Test fun `open review page`() {
        page.allReviewsBtn.click()
        assertTrue(driver.isExist(By.xpath("//h1[text() = 'Отзывы о банках']")))
    }
}