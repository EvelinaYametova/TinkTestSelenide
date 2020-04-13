import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.jupiter.api.Order;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiTest {
    @Test
    @Order(1)
     public void OK200Test() {//12
        RestAssured.get("https://www.cbr-xml-daily.ru/daily_json.js")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(2)
    public void headerTest() {//13
        RestAssured.get("https://www.cbr-xml-daily.ru/daily_json.js")
                .then()
                .header("Content-Type","application/javascript; charset=utf-8");
    }

    @Test
    @Order(3)
    public void isValidate() {//14
        RestAssured.get("https://www.cbr-xml-daily.ru/daily_json.js")
                .then()
                .body(matchesJsonSchemaInClasspath("schema.json"));

    }

    @Test
    @Order(4)
    public void dateTimestampCorrect() {//15  D
        RestAssured.get("https://www.cbr-xml-daily.ru/daily_json.js")
                .then()
                .body("Date", equalTo("2020-04-14T11:30:00+03:00"))
                .and()
                .body("Timestamp", equalTo("2020-04-13T14:00:00+03:00"));
    }
}
