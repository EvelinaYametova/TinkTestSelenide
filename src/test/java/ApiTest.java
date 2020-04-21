import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import json.*;


import java.text.ParseException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTest {
    private static final String PATH = "https://www.cbr-xml-daily.ru/daily_json.js";
    private Response response = RestAssured.get(PATH);

    @Test
    public void testApi() throws ParseException {
        OK200Test();
        headerTest();
        isValidate();
        dateTimestampCorrect();
    }

    @Step("������ � ��� �� ���������� � ����� 200")
     public void OK200Test() {//12
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Step("��������� Content-Type ������������ ����������������")
    public void headerTest() {//13
        response.then().contentType(ContentType.JSON);
    }

    @Step("� ������ ����� ���������� ������� \"USD\" � \"EUR\"")
    public void isValidate() {//14
        response.then().body(matchesJsonSchemaInClasspath("schema.json"));
    }

    @Step("� \"Date\" ������������ ������� ����, � \"Timestamp\" - ������")
    public void dateTimestampCorrect() throws ParseException {//15
        String str = response.asString();
        ExchangeData exchangeData = new Gson().fromJson(str, ExchangeData.class);
        assertTrue(exchangeData.getDate().after(exchangeData.getTimestamp()));
    }
}