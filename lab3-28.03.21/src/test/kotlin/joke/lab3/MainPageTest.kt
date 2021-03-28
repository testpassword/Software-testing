package joke.lab3

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.interactions.Actions

class MainPageTest {

    private lateinit var firefox: WebDriver
    //private lateinit var chrome: WebDriver
    private lateinit var mainPage: MainPage
    private val URL = "https://www.jetbrains.com/"

    // https://www.browserstack.com/guide/run-selenium-tests-using-firefox-driver
    @BeforeEach fun setUp() {
        WebDriverManager.firefoxdriver().setup()
        firefox = FirefoxDriver()
        //WebDriverManager.chromiumdriver().setup()
        //chrome = ChromeDriver()
        firefox.manage().window().maximize()
        firefox.get(URL)
        //chrome.get(URL)
        mainPage = MainPage(firefox)
    }

    @AfterEach
    fun tearDown() = firefox.quit()

    @Test
    fun search() {
        mainPage.searchButton.click()
        val searchField = firefox.findElement(By.id("header-search"))
        searchField.sendKeys("Selenium")
        val submitButton = firefox.findElement(By.xpath("//button[@type='submit' and text()='Search']"))
        submitButton.click()
        val searchPageField = firefox.findElement(By.className("js-search-input"))
        assertEquals("Selenium", searchPageField.getAttribute("value"))
    }

    @Test
    fun toolsMenu() {
        Actions(firefox).moveToElement(mainPage.toolsMenu).perform()
        val menuPopup = firefox.findElement(By.className("menu-main__popup-wrapper"))
        assertTrue(menuPopup.isDisplayed)
    }

    @Test
    fun navigationToAllTools() {
        mainPage.seeAllToolsButton.click()
        val productsList = firefox.findElement(By.className("products-list"))
        assertTrue(productsList.isDisplayed)
        assertEquals("All Developer Tools and Products by JetBrains", firefox.title)
    }
}
