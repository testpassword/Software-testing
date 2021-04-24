package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class ExchangePage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.banki.ru/products/currency/"

    @FindBy(xpath = "//input[@class = 'form-input-field' and @data-bind = 'first-input']")
    lateinit var fCurValInput: WebElement

    @FindBy(xpath = "//input[@class = 'form-input-field' and @data-bind = 'second-input']")
    lateinit var sCurValInput: WebElement

    @FindBy(xpath = "//input[@data-bind = 'first-input']/parent::*/div/span")
    lateinit var fCurSelectorLbl: WebElement

    @FindBy(xpath = "//span[@data-test = 'converter-swap-fields']")
    lateinit var curSwapper: WebElement

    @FindBy(xpath = "//div[@class = 'ui-select__list']/div[2]/span[1]")
    lateinit var curListSelectorFirstCell: WebElement

    @FindBy(xpath = "//a[contains(@class, 'button') and @data-test = 'all-popular-currency']")
    lateinit var allCurRateBtn: WebElement

    @FindBy(xpath = "//a[text() = 'JPY']")
    lateinit var curExtraLink: WebElement
}