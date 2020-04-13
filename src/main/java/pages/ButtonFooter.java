package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ButtonFooter {
    private final ElementsCollection footerTitles = $$(".footer__2YyQ1");
    private final ElementsCollection footerItems = $$(".footer__3mWH-");
    private final ElementsCollection networkItems = $$(".footer__2cPgR");
    private final SelenideElement btnLanguage = $(".footer__59O-3");

    private static final int FOOTER_TITLES_SIZE = 6;
    private static final int FOOTER_ITEMS_SIZE = 37;
    private static final int NETWORK_ITEMS_SIZE = 6;

    public ButtonFooter loaded() {
        footerTitles.shouldHaveSize(FOOTER_TITLES_SIZE);
        footerItems.shouldHaveSize(FOOTER_ITEMS_SIZE);
        networkItems.shouldHaveSize(NETWORK_ITEMS_SIZE);
        return this;
    }

    public ButtonFooter clickabled() {
        footerTitles
                .filter(Condition.visible)
                .filter(Condition.enabled)
                .shouldHaveSize(FOOTER_TITLES_SIZE);
        footerItems
                .filter(Condition.visible)
                .filter(Condition.enabled)
                .shouldHaveSize(FOOTER_ITEMS_SIZE);
        networkItems
                .filter(Condition.visible)
                .filter(Condition.enabled)
                .shouldHaveSize(NETWORK_ITEMS_SIZE);
        return this;
    }

    public ButtonFooter btnLanguageIsCorrect() {
        btnLanguage
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.text("English"));
        return this;
    }
}
