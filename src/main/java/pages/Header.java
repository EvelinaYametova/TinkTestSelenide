package pages;

import com.codeborne.selenide.Driver;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;

public class Header {
    private final ElementsCollection topHeader = $$("span[data-qa-type='uikit/navigation.firstMenuItemTitle']");
    private final ElementsCollection buttonHeader = $$("span[data-qa-type='uikit/navigation.secondMenuItemTitle']");
    private final SelenideElement menuSection = $(".header__Dx08O");
    private static final String MENU_SECTION_TITLE = "Курсы валют";
    private static final String[] BUTTON_TITLES = { "Курсы валют", "Новости", "Руководство", "Инвесторам",
            "Документы", "Реквизиты", "Бренд Тинькофф"};
    private static final String[] TOP_TITLES = { "Банк", "Бизнес", "Инвестиции", "Страхование", "Мобайл", "", "" };
    private static final String[] LINKS = {"https://www.tinkoff.ru/", "https://www.tinkoff.ru/business/", "https://www.tinkoff.ru/invest/",
    "https://www.tinkoff.ru/insurance/", "https://www.tinkoff.ru/mobile-operator/", "https://www.tinkoff.ru/about/news/",
    "https://www.tinkoff.ru/about/management/", "https://www.tinkoff.ru/about/investors/11/", "https://www.tinkoff.ru/about/documents/",
    "https://www.tinkoff.ru/about/properties/", "https://www.tinkoff.ru/about/brand/"};


    public static Condition css(final String propName, final String propValue) {
        return new Condition("css") {
            @Override
            public boolean apply(Driver driver, WebElement element) {
                return propValue.equalsIgnoreCase(element.getCssValue(propName));
            }
        };
    }

    public Header highlited() {
        menuSection.shouldHave(css("border-bottom-color", "rgba(51, 51, 51, 1)"));
        return this;
    }

    public Header loaded() {
        int index = 0;
        for (SelenideElement elem : buttonHeader) {
            elem.shouldHave(Condition.text(BUTTON_TITLES[index]));
            index++;
        }
        index = 0;
        for (SelenideElement elem : topHeader) {
            elem.shouldHave(Condition.text(TOP_TITLES[index]));
            index++;
        }
        return this;
    }

    public Header linksIsAvailabled() {
        for (String link : LINKS) {
                RestAssured.get(link).then().statusCode(HttpStatus.SC_OK);
        }
        return this;
    }

    public SelenideElement getMenuSection() {
        return menuSection;
    }

    public String getMenuSectionTitle() {
        return MENU_SECTION_TITLE;
    }
}
