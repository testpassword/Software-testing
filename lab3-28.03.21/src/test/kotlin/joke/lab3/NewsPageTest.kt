package joke.lab3

import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class NewsPageTest(browserType: Class<RemoteWebDriver>): AbstractPageTest(browserType) {

    private lateinit var page: NewsPage

    override fun setup() {
        with(DriverFactory.create(NewsPage::class.java, browserType)) {
            driver = first
            page = second as NewsPage
        }
        driver.get(page.url)
    }

    @Test fun `show detailed news item`() {
        val title = page.showNewsItemBtn.text
        page.showNewsItemBtn.click()
        assertEquals(title, driver.findElement(By.xpath("//h1")).text)
    }

    @Test fun `show main news`() {
        page.mainTabBtn.click()
        assertTrue(driver isExist By.xpath("//h1[text() = 'Главные новости']"))
    }

    @Test fun `share news item`() {
        page.showNewsItemBtn.click()
        val newsItemWindow = driver.windowHandle
        val shareBtn = driver.findElements(By.xpath("//div[@data-load-count]")).random()
        shareBtn.click()
        assertTrue(driver.windowHandles.any { it != newsItemWindow })
    }

    @Test fun subscribe() {
        val email = "lol@kek.rolf"
        page.subsEmailInput.sendKeys(email)
        page.subsAcceptBtn.click()
        TimeUnit.SECONDS.sleep(2)
        assertTrue(
            listOf(
                By.xpath("//div[@class = 'quick-subscribe__error-text' and (contains(text(), 'Спасибо за подписку!') or contains(text(), 'Email уже подписан'))]"),
                By.xpath("//div[contains(@class, 'ui-alert--success')]")
            ).any { driver isExist it }
        )
    }
}