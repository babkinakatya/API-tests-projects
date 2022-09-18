package Homeworks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Ex5 {

    @Test
    public void PrintSecondText(){
        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();
        Map<String, String> jsonStr = response.get("messages[1]");
        String message = jsonStr.get("message");

        System.out.println(message);

    }

}
