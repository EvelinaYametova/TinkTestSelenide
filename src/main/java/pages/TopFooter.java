package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TopFooter {
    private final SelenideElement topFooterTitle = $(".footer__2L4IY");
    private final ElementsCollection topFooterItems = $$(".footer__1J191");
    private static final int TOP_FOOTER_ITEMS_SIZE = 9;

    public TopFooter loaded() {
        topFooterItems.shouldHaveSize(TOP_FOOTER_ITEMS_SIZE);
        return this;
    }

    public TopFooter clickabled() {
        topFooterTitle
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled);
        topFooterItems
                .filter(Condition.visible)
                .filter(Condition.enabled)
                .shouldHaveSize(TOP_FOOTER_ITEMS_SIZE);
        return this;
    }
}
