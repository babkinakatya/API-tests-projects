package Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Ex4 {

    @Test
    public void testSendGetRequest(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();
        System.out.println(response.body().asString());
    }

}
