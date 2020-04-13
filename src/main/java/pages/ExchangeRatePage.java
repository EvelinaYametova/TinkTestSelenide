package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ExchangeRatePage {
    private Footer footer = new Footer();
    private Header header = new Header();
    private Main main = new Main();
    private SelenideElement pageTitle = $(byXpath("/html/head/title"));

    public Footer getFooter() {
        return footer;
    }

    public Header getHeader() {
        return header;
    }

    public Main getMain() {
        return main;
    }

    public SelenideElement getPageTitle() {
        return pageTitle;
    }
}
