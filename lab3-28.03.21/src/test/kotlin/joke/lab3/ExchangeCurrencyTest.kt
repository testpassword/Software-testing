package joke.lab3

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver

class ExchangeCurrencyTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: ExchangePage

    @Before override fun setup() {
        with(DriverFactory.create(ExchangePage::class.java, browserType)) {
            driver = first
            page = second as ExchangePage
        }
        driver.get(page.url)
        page.fCurValInput.clear()
        page.sCurValInput.clear()
    }

    @Test fun `exchange with correct data`() {
        page.fCurValInput.sendKeys("123")
        assertNotNull(page.sCurValInput["value"].numeric)
    }

    @Test fun `exchange with incorrect data`() {
        page.fCurValInput.sendKeys("kek")
        println(page.fCurValInput.text)
        assertEquals(0.0, page.sCurValInput["value"].numeric)
    }

    @Test fun `currency changing`() {
        page.fCurSelectorLbl.click()
        val newCurName = page.curListSelectorFirstCell.text
        page.curListSelectorFirstCell.click()
        assertEquals(newCurName, page.fCurSelectorLbl.text)
    }

    @Test fun `currency swapping`() {
        val oldCurNames = page.fCurSelectorLbl.text to page.sCurSelectorLbl.text
        page.curSwapper.click()
        val newCurNames = page.fCurSelectorLbl.text to page.sCurSelectorLbl.text
        assertNotEquals(oldCurNames, newCurNames)
    }

    @Test fun `show currency exchange rate table`() {
        page.allCurRateBtn.click()
        assertTrue(driver isExist By.xpath("//div[@class = 'widget' and @data-test = 'currency-table']"))
    }

    @Test fun `show currency extra info`() {
        page.curExtraLink.click()
        assertTrue(driver isExist By.xpath("//section[@class = 'widget' and @data-test = 'current-currency']"))
    }
}