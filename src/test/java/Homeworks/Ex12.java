package Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.BaseTestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Ex12 extends BaseTestCase {

    @Test
    public void checkHeader() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();

        Assert.assertEquals("Header value is not hw_value ", "Some secret value",
                this.getHeader(response, "x-secret-homework-header"));
    }
}
