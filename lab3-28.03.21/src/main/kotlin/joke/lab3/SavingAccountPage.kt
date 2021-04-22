package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SavingAccountPage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.banki.ru/products/deposits/catalogue/nakopitelnyie_scheta/"

    @FindBy(xpath = "//input[@data-role = 'input' and @type = 'tel']")
    lateinit var moneyInput: WebElement

    @FindBy(xpath = "//button[@data-role = 'button' and @type = 'button' and contains(@class, 'fnbbcZ')]")
    lateinit var configureBtn: WebElement

    @FindBy(xpath = "//div[contains(@class, 'ctZjql')]/span")
    lateinit var amountOfResults: WebElement

    @FindBy(xpath = "//a[@data-role = 'link' and starts-with(@href, '/products/')]")
    lateinit var showAllSavingsBtn: WebElement
}
