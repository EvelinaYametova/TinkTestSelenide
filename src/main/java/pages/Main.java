package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Main {
    private final SelenideElement firstSelector = $$(".InputBox__box_3p-Uw").first();
    private final SelenideElement secondSelector = $$(".InputBox__box_3p-Uw").get(1);

    private final SelenideElement firstColumn = $$(".DesktopExchange__th_AXtbR").get(1);
    private final SelenideElement secondColumn = $$(".DesktopExchange__th_AXtbR").get(2);

    private final SelenideElement tableValue = $$(".Table__valign_bottom_1XlMR").get(1);

    public SelenideElement getFirstSelector() {
        return firstSelector;
    }

    public SelenideElement getSecondSelector() {
        return secondSelector;
    }

    public SelenideElement getFirstColumn() {
        return firstColumn;
    }

    public SelenideElement getSecondColumn() {
        return secondColumn;
    }

    public SelenideElement getTableValue() {
        return tableValue;
    }

    public void changeFirstSelectorOnEUR() {
        firstSelector.click();
        $(byText("Евро")).click();
    }

    public void changeFirstSelectorOnUSD() {
        firstSelector.click();
        $(byText("Доллар")).click();
    }

    public void changeSecondSelectorOnUSD() {
        secondSelector.click();
        $(byText("Доллар")).click();
    }
}
