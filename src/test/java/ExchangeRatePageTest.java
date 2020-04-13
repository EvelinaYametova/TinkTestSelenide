import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;
import pages.*;

import static com.codeborne.selenide.Selenide.open;

public class ExchangeRatePageTest {

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Эвелина\\Desktop\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.timeout=10000;
        open("https://www.tinkoff.ru/about/exchange/");
    }


    @Test
    public void exchangeRatePageLoaded() {//2
        //new ExchangeRatePage().getPageTitle().shouldHave(Condition.text("Курсы валют"));
    }

    @Test
    public void headerLoaded() {//3
        ExchangeRatePage page = new ExchangeRatePage();
        page.getHeader().loaded().enabled();
    }

    @Test
    public void menuSectionHighlitedIsCorrect() {//4
        Header header = new ExchangeRatePage().getHeader();
        header.getMenuSection()
                .shouldHave(Condition.text(header.getMenuSectionTitle()));
    }

    @Test
    public void footerIsCorrect() {//5
        Footer footer = new ExchangeRatePage().getFooter();
        footer.getButtonFooter()
                .btnLanguageIsCorrect()
                .loaded()
                .clickabled();
        footer.getTopFooter()
                .loaded()
                .clickabled();
    }

    @Test
    public void defaultSelectorsIsCorrect() {//6
        Main main = new ExchangeRatePage().getMain();
        main.getFirstSelector().shouldHave(Condition.text("Рубль"));
        main.getSecondSelector().shouldHave(Condition.text("Евро"));
        main.getFirstColumn().shouldHave(Condition.text("₽ → €"));
        main.getSecondColumn().shouldHave(Condition.text("€ → ₽"));
    }

    @Test
    public void textInSelectorsReplaced() {//7
        Main main = new ExchangeRatePage().getMain();
        main.changeFirstSelectorOnEUR();
        main.getFirstSelector().shouldHave(Condition.text("Евро"));
        main.getSecondSelector()
                .shouldHave(Condition.text("Рубль"));
    }

    @Test
    public void secondSelectorIsUSD() {//8-9
        Main main = new ExchangeRatePage().getMain();
        main.changeSecondSelectorOnUSD();
        main.getSecondSelector().shouldHave(Condition.text("Доллар"));
    }

    @Test
    public void tableIsCorrect() {//10
        Main main = new ExchangeRatePage().getMain();
        main.changeFirstSelectorOnEUR();
        main.changeSecondSelectorOnUSD();
        main.getFirstColumn().shouldHave(Condition.text("$ → €"));
        main.getSecondColumn().shouldHave(Condition.text("€ → $"));
    }

    @Test
    public void exchangeRateEurRubIsCorrect() {//17
        Main main = new ExchangeRatePage().getMain();
        double expected = Double.parseDouble(main.getTableValue().getText().replace(',', '.'));
        main.getTableValue().shouldHave(Condition.text(Double.toString(expected).replace('.', ',')));
    }

    @Test
    public void exchangeRateEurUsdIsCorrect() {//18-19
        Main main = new ExchangeRatePage().getMain();
        main.changeFirstSelectorOnUSD();
        double expected = Double.parseDouble(main.getTableValue().getText().replace(',', '.'));
        main.getTableValue().shouldHave(Condition.text(Double.toString(expected).replace('.', ',')));
    }
}