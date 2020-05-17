package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyListTests extends CoreTestCase {
    private static final String name_of_folder = "Learning Programming";

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelButton();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()) {
            MyListsPageObject.clickClosePopUpButton();
        }

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testEx5SaveTwoArticles() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.waitForTitleElement();
        }

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isIOS()) {
            SearchPageObject.clickClearTextBtn();
        }
        SearchPageObject.initSearchInput();
        String secondArticle = "JavaScript";
        SearchPageObject.typeSearchLine(secondArticle);

        if(Platform.getInstance().isIOS()) {
            SearchPageObject.clickByArticleWithSubstring("High-level programming language");
        } else {
            SearchPageObject.clickByArticleWithSubstring("Programming language");
        }

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToExistingFolder(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelButton();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()) {
            MyListsPageObject.clickClosePopUpButton();
        }

        if(Platform.getInstance().isIOS()) {
            List<WebElement> number_of_articles_before_removal = MyListsPageObject.getNumberOfArticlesInTheList("//XCUIElementTypeCell");
            assertTrue("Unexpected number of articles before removing an article from Saved", number_of_articles_before_removal.size() > 1);
        }

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
            MyListsPageObject.swipeByArticleToDelete(secondArticle);

            String expected_article_title = "Java (programming language)";
            String actual_article_title = MyListsPageObject.getArticleTitleInFolder();

            assertEquals("Unexpected article title",
                    expected_article_title,
                    actual_article_title);
        } else {
            MyListsPageObject.swipeByArticleToDelete(secondArticle);
            List<WebElement> actual_number_of_articles_after_removal = MyListsPageObject.getNumberOfArticlesInTheList("//XCUIElementTypeCell");
            assertTrue("Number of articles left is incorrect", actual_number_of_articles_after_removal.size() == 1);
        }
    }
}
