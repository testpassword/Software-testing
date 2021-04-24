package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class MainPage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.banki.ru/"

    @FindBy(xpath = "//div[@class = 'header-search']")
    lateinit var mainSearchBtn: WebElement

    @FindBy(xpath = "//input[@type = 'search' and @data-test = 'input-search']")
    lateinit var overlaySearchInput: WebElement

    @FindBy(xpath = "//span[@data-geo-selector = '']/span")
    lateinit var geoSelectorBtn: WebElement

    @FindBy(xpath = "//span[@class = '_86KdL']")
    lateinit var cityLbl: WebElement

    @FindBy(xpath = "//span[contains(@class, 'drnpFB')]")
    lateinit var changeCityBtn: WebElement
}