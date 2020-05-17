package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCancelButtonDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getNumberOfFoundArticles();
        assertTrue("We found too few results!", amount_of_search_results > 0);

    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "zxcvasdfqwer";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testEx3CancelSearchResult() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("California");

        if(Platform.getInstance().isIOS()) {
            List results = SearchPageObject.getSearchResultList("//XCUIElementTypeCell");
            assertTrue("Search result is less than 3", results.size() >= 3);
        } else {
            List results = SearchPageObject.getSearchResultList("id:org.wikipedia:id/page_list_item_title");
            System.out.println(results.size());
            assertTrue("Search result is less than 3", results.size() >= 3);
        }

        SearchPageObject.clickCancelButton();
        SearchPageObject.cancelSearchResultList();
    }

    @Test
    public void testEx9TemplateRefactoring() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Wine");
        SearchPageObject.waitForElementByTitleAndDescription("Wine", "Alcoholic drink made from grapes");

        if(Platform.getInstance().isIOS()) {
            List results = SearchPageObject.getSearchResultList("//XCUIElementTypeCell");
            assertTrue("Search result is less than 3", results.size() >= 3);
        } else {
            List results = SearchPageObject.getSearchResultList("id:org.wikipedia:id/page_list_item_title");
            assertTrue("Search result is less than 3", results.size() >= 3);
        }
    }
}
