package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

abstract class PageObject(driver: WebDriver) {

    abstract val url: String

    init { PageFactory.initElements(driver, this) }
}