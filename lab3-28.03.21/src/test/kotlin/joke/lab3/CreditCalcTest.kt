package joke.lab3

import org.junit.Test
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver

class CreditCalcTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: CreditCalcPage

    @Before override fun setup() {
        with(DriverFactory.create(CreditCalcPage::class.java, browserType)) {
            driver = first
            page = second as CreditCalcPage
        }
        driver.get(page.url)
    }

    @Test fun `loan master test`() {
        val oldLoanRate = page.loanRateLbl.text
        val oldPayment = page.paymentLbl.text
        page.depositList.click()
        driver.findElement(By.xpath("//div[@class = 'deposit']/div/div[2]/div/div[2]/div/div[1]/ul/li[2]")).click()
        page.timeList.click()
        driver.findElement(By.xpath("//div[@data-test = 'credit-period']/div/div[2]/div/div[2]/div/div[1]/ul/li[2]")).click()
        assertAll(
            { assertNotEquals(oldLoanRate, page.loanRateLbl.text) },
            { assertNotEquals(oldPayment, page.paymentLbl.text) }
        )
    }
}