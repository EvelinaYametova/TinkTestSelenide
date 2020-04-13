package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Header {
    private final ElementsCollection topHeader = $$(".header__3KA3q");
    private final ElementsCollection buttonHeader = $$(".header__1L2Qt");
    private final SelenideElement menuSection = $(".header__3w71t");

    private static final int TOP_SIZE = 9;
    private static final int BUTTON_SIZE = 10;
    private static final String MENU_SECTION_TITLE = "Курсы валют";

    public Header loaded() {
        topHeader.shouldHaveSize(TOP_SIZE);
        buttonHeader.shouldHaveSize(BUTTON_SIZE);
        return this;
    }

    public Header enabled() {
        topHeader.filter(Condition.enabled).shouldHaveSize(TOP_SIZE);
        buttonHeader.filter(Condition.enabled).shouldHaveSize(BUTTON_SIZE);
        return this;
    }

    public SelenideElement getMenuSection() {
        return menuSection;
    }

    public String getMenuSectionTitle() {
        return MENU_SECTION_TITLE;
    }

}
