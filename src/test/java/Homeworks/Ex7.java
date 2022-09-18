package Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Ex7 {

    @Test
    public void RedirectAddress() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();

        String locationHeader = response.getHeader("Location");
        System.out.println(locationHeader);

        int count = 1;

        while (locationHeader != null) {
            Response nextResponse = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(locationHeader)
                    .andReturn();

            int statusCode = response.getStatusCode();
            if (statusCode != 200) {

                locationHeader = nextResponse.getHeader("Location");

                if (locationHeader != null) {

                System.out.println(locationHeader);
                count = count + 1;
                }
            }
        }
        System.out.println("Количество редиректов: " + count);
    }
}
