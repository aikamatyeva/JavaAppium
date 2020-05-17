package lib.ui;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

     protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESC_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_LIST_ITEM_TITLE_ELEMENT,
             SEARCH_INPUT_CLEAR_TEXT;

    private Object SearchResultList;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /*TPL = TEMPLATE METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndDesc(String title, String description) {
        String interim = SEARCH_RESULT_BY_TITLE_AND_DESC_TPL.replace("{SUBSTRING}", title);
        String searchResultByTitleAndDescElement = interim.replace("{SUBSTRING2}", description);
        return searchResultByTitleAndDescElement;
    }
    /*TPL = TEMPLATE METHODS */

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementByTitleAndDesc(title, description);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + title + "and " + description);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking init element");

    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void waitForCancelButtonDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void clickClearTextBtn() {
        this.waitForElementAndClick(
                SEARCH_INPUT_CLEAR_TEXT,
                "Cannot find and click search clear button",
                10);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                10);
    }

    public int getNumberOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result label by the request",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotFound(SEARCH_RESULT_ELEMENT, "We are not supposed to find any results");
    }

    public List getSearchResultList(String locator) throws InterruptedException {
        Thread.sleep(2000);
        if(Platform.getInstance().isAndroid()) {
            List<WebElement> results = driver.findElements(By.id(locator));
            return results;
        } else {
            List<WebElement> results = driver.findElements(By.xpath(locator));
            return results;
        }
    }

    public void cancelSearchResultList() {
        this.waitForElementNotPresent(
                SEARCH_LIST_ITEM_TITLE_ELEMENT,
                "Search result is still present",
                5
        );
    }
}
