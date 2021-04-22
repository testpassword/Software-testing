package joke.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class BankRatePage(driver: WebDriver): PageObject(driver) {

    override val url = "https://www.banki.ru/services/responses/"

    @FindBy(xpath = "//a[@data-test = 'main-tab']")
    lateinit var mainRateBtn: WebElement

    @FindBy(xpath = "//a[@data-test = 'champ-tab']")
    lateinit var champRateBtn: WebElement

    @FindBy(xpath = "//table[contains(@class, 'banks-list-table full-table')]")
    lateinit var bankRateTbl: WebElement

    @FindBy(xpath = "//div[contains(@class, 'tt-menu')]")
    lateinit var searchCandidatesList: WebElement

    @FindBy(xpath = "//input[@id = 'bank-search-field']")
    lateinit var tableSearchInput: WebElement

    @FindBy(xpath = "//a[@class = 'button button--bordered button--size_small' and starts-with(@href, '/services/')]")
    lateinit var allReviewsBtn: WebElement
}