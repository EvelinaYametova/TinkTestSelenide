package pages;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;

public class Footer {
    private static final String[] LINKS = {
            "https://www.tinkoff.ru/about/",
            "https://www.tinkoff.ru/career/",
            "https://www.tinkoff.ru/maps/payment/",
            "https://www.tinkoff.ru/maps/atm/",
            "https://www.tinkoff.ru/contacts/",
            "https://help.tinkoff.ru/",
            "https://www.tinkoff.ru/eng/ir/"
    };

    public Footer linksIsAvailabled() {
        for (String link : LINKS) {
            RestAssured.get(link).then().statusCode(HttpStatus.SC_OK);
        }
        return this;
    }
}