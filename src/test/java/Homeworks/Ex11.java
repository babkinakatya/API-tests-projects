package Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.BaseTestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Ex11 extends BaseTestCase {

    @Test
    public void checkCookie(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        Assert.assertEquals("Cookie value is not hw_value ", "hw_value",
                this.getCookie(response, "HomeWork"));
    }
}
