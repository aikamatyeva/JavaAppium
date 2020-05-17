package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {
     static  {
                 SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
                 SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
                 SEARCH_CANCEL_BUTTON = "id:Cancel";
                 SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
                 SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://*[@text='{SUBSTRING}']/../*[@text='{SUBSTRING2}']";
                 SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
                 SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
                 SEARCH_LIST_ITEM_TITLE_ELEMENT = "id:org.wikipedia:id/page_list_item_title";
                SEARCH_INPUT_CLEAR_TEXT = "xpath://XCUIElementTypeButton[@name='Clear text']";
     }

    public iOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
