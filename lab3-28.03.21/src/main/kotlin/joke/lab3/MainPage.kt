package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class MainPage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.banki.ru/"

    @FindBy(xpath = "//span[@data-click = 'open-search' and @data-test = 'header-search']")
    lateinit var mainSearchBtn: WebElement

    @FindBy(xpath = "//input[@type = 'search' and @data-test = 'input-search']")
    lateinit var overlaySearchInput: WebElement

    @FindBy(xpath = "//button[@type = 'submit' and @data-test = 'submit-search-button']")
    lateinit var overlaySearchBtn: WebElement
}