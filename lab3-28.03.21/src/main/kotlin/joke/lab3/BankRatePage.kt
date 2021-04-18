package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class BankRatePage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.banki.ru/services/responses/"

    @FindBy(xpath = "//span[@data-test = 'main-tab']")
    lateinit var mainRateBtn: WebElement

    @FindBy(xpath = "//span[@data-test = 'champ-tab']")
    lateinit var champRateBtn: WebElement
}