package joke.lab3

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class NewsPage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.banki.ru/news/lenta"

    @FindBy(xpath = "//a[@class = 'section-menu__link' and contains(@href, '/main/')]")
    lateinit var mainTabBtn: WebElement

    @FindBy(xpath = "//a[@class = 'text-list-link' and contains(@href, 'id=')][1]/span")
    lateinit var showNewsItemBtn: WebElement

    @FindBy(xpath = "//input[@class = 'quick-subscribe__field']")
    lateinit var subsEmailInput: WebElement

    @FindBy(xpath = "//input[@class = 'quick-subscribe__button' and @type = 'submit']")
    lateinit var subsAcceptBtn: WebElement
}