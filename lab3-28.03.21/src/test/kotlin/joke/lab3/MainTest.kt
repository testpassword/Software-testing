package joke.lab3

import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.RemoteWebDriver
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By

class MainTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: MainPage

    @Before override fun setup() {
        with(DriverFactory.create(MainPage::class.java, browserType)) {
            driver = first
            page = second as MainPage
        }
        driver.get(page.url)
    }

    @Test fun `test search`() {
        page.mainSearchBtn.click()
        page.overlaySearchInput.sendKeys("test")
        page.overlaySearchBtn.click()
        assertTrue(driver.isExist(By.xpath("//h1[text() = 'Поиск по сайту']")))
    }
}