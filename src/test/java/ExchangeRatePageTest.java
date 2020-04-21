import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import json.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.open;

public class ExchangeRatePageTest {

    private static final String PATH = "https://www.cbr-xml-daily.ru/daily_json.js";
    private Response response = RestAssured.get(PATH);

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "..\\config\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.timeout=10000;
        open("https://www.tinkoff.ru/about/exchange/");
    }

    @Test
    public void testUI() {
        exchangeRatePageLoaded();
        headerLoaded();
        menuSectionHighlitedIsCorrect();
        defaultSelectorsIsCorrect();
        textInSelectorsReplaced();
        secondSelectorIsUSD();
        tableIsCorrect();
        exchangeRateEurRubIsCorrect();
        exchangeRateEurUsdIsCorrect();
    }

    @Step("Страница действительно загрузилась")
    public void exchangeRatePageLoaded() {//2
        assertEquals("Курсы валют", Selenide.title());
    }

    @Step("Хэдер отображается корректно")
    public void headerLoaded() {//3
        ExchangeRatePage page = new ExchangeRatePage();
        page.getHeader().loaded().linksIsAvailabled();
    }

    @Step("Выделен раздел \"Курсы валют\"")
    public void menuSectionHighlitedIsCorrect() {//4
        Header header = new ExchangeRatePage().getHeader();
        header.highlited()
                .getMenuSection()
                .shouldHave(Condition.text(header.getMenuSectionTitle()));
    }

    @Step("По умолчанию в селекторах выбора валют выставлены Рубль-->Евро соотвественно, а в таблице курс Евро к Рублю")
    public void defaultSelectorsIsCorrect() {//6
        Main main = new ExchangeRatePage().getMain();
        main.getFirstSelector().shouldHave(Condition.text("Рубль"));
        main.getSecondSelector().shouldHave(Condition.text("Евро"));
        main.getFirstColumn().shouldHave(Condition.text("₽ → €"));
        main.getSecondColumn().shouldHave(Condition.text("€ → ₽"));
    }

    @Step("Во 2-м селекторе значение поменялось на Рубль")
    public void textInSelectorsReplaced() {//7
        Main main = new ExchangeRatePage().getMain();
        main.changeFirstSelectorOnEUR();
        main.getFirstSelector().shouldHave(Condition.text("Евро"));
        main.getSecondSelector()
                .shouldHave(Condition.text("Рубль"));
    }

    @Step("В селекте отображается Доллар")
    public void secondSelectorIsUSD() {//8-9
        Main main = new ExchangeRatePage().getMain();
        main.changeSecondSelectorOnUSD();
        main.getSecondSelector().shouldHave(Condition.text("Доллар"));
    }

    @Step("В таблице отображается курс Евро к Доллару")
    public void tableIsCorrect() {//10
        Main main = new ExchangeRatePage().getMain();
        main.changeFirstSelectorOnEUR();
        main.changeSecondSelectorOnUSD();
        main.getFirstColumn().shouldHave(Condition.text("$ → €"));
        main.getSecondColumn().shouldHave(Condition.text("€ → $"));
    }

    @Step("Курс Евро к Рублю отображается в соответствии с курсом ЦБ")
    public void exchangeRateEurRubIsCorrect() {//17
        open("https://www.tinkoff.ru/about/exchange/");
        Main main = new ExchangeRatePage().getMain();
        double actual = Double.parseDouble(main.getTableValue().getText().replace(',', '.'));
        String str = response.asString();
        ExchangeData exchangeData = new Gson().fromJson(str, ExchangeData.class);
        double expected = exchangeData.getValute().getEur().getValue();
        assertEquals(expected, actual, 3.0);
    }

    @Step("Курс Евро к Доллару отображается корректно")
    public void exchangeRateEurUsdIsCorrect() {//18-19
        open("https://www.tinkoff.ru/about/exchange/");
        Main main = new ExchangeRatePage().getMain();
        main.changeFirstSelectorOnUSD();
        double actual = Double.parseDouble(main.getTableValue().getText().replace(',', '.'));
        String str = response.asString();
        ExchangeData exchangeData = new Gson().fromJson(str, ExchangeData.class);
        double usd = exchangeData.getValute().getUsd().getValue();
        double eur = exchangeData.getValute().getEur().getValue();
        double expected = eur / usd;
        assertEquals(expected, actual, 0.5);
    }
}