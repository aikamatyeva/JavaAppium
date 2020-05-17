package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://*[@text='{SUBSTRING}']/../*[@text='{SUBSTRING2}']";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_LIST_ITEM_TITLE_ELEMENT = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_INPUT_CLEAR_TEXT = "xpath://XCUIElementTypeButton[@name='Clear text']";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
