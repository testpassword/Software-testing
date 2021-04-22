package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class CreditCalcPage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.banki.ru/credit-master/?source=slider_mpk&lp=slider_mpk"

    @FindBy(xpath = "//div[@data-test = 'deposit']")
    lateinit var depositList: WebElement

    @FindBy(xpath = "//div[@data-test = 'credit-period']")
    lateinit var timeList: WebElement

    @FindBy(xpath = "//div[@data-test = 'rate']/div/div[2]")
    lateinit var loanRateLbl: WebElement

    @FindBy(xpath = "//div[@data-test = 'payment']/div/div[2]")
    lateinit var paymentLbl: WebElement

    @FindBy(xpath = "//button[@type = 'submit']")
    lateinit var calcLoanBtn: WebElement
}