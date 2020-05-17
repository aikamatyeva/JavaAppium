package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListPageObject extends MyListsPageObject {
    static {
                ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
                POPUP_CLOSE_BTN = "xpath://XCUIElementTypeButton[@name='Close']";
    }

    public iOSMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
