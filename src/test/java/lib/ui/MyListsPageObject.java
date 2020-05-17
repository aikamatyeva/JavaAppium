package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            POPUP_CLOSE_BTN,
            ARTICLE_TITLE_IN_FOLDER;

    public WebElement waitForArticleTitleElement() {
        if (Platform.getInstance().isAndroid()) {
            return this.waitForElementPresent(ARTICLE_TITLE_IN_FOLDER, "Cannot find the article title in the folder!", 15);
        } else {
            return this.waitForElementPresent(ARTICLE_BY_TITLE_TPL, "Cannot find the article title in the folder!", 15);
        }
    }

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public String getArticleTitleInFolder() {
        WebElement title_element = waitForArticleTitleElement();
        return title_element.getAttribute("text");
    }

    public List getNumberOfArticlesInTheList(String locator) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> results = driver.findElements(By.xpath(locator));
        return results;
    }

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find a folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title",
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article is still present with title",
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.swipeElementToLeft(
                article_xpath,
                "Cannot find and delete saved article"
        );
        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void clickClosePopUpButton() {
        this.waitForElementAndClick(POPUP_CLOSE_BTN, "Cannot find and click my lists close button", 5);
    }
}
