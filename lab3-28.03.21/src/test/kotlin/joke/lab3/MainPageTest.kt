package joke.lab3

import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.RemoteWebDriver
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import java.util.concurrent.TimeUnit

class MainPageTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

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
        page.overlaySearchInput.also {
            it.sendKeys("test")
            it.sendKeys(Keys.ENTER)
        }
        assertTrue(driver isExist By.xpath("//h1[text() = 'Поиск по сайту']"))
    }

    @Test fun `change city`() {
        page.geoSelectorBtn.click()
        page.changeCityBtn.click()
        val newCityBtn = driver.findElement(By.xpath("//li[@class = '_1xByn']"))
        val newCity = newCityBtn.text
        newCityBtn.click()
        page.geoSelectorBtn.click()
        assertEquals(page.cityLbl.text, newCity)
    }
}