package Homeworks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Ex8 {
    int time;
    String token;

    @Test
    public void testGetDoneTask() throws InterruptedException {

        submitTask();

        if (token != null) {
            Thread.sleep(time*1000);

            JsonPath response = RestAssured
                    .given()
                    .get("https://playground.learnqa.ru/ajax/api/longtime_job?token=" + token)
                    .jsonPath();

            String status = response.get("status");
            String result = response.get("result");

            Assert.assertEquals("Job is ready", status);
            System.out.println("Status: " + status);

            Assert.assertTrue("Result is null", result != null);
            System.out.println("Result: " + result);
        }
    }

    @Test
    public void testGetProcessTask() throws InterruptedException {

        submitTask();
        if (token != null) {

            Thread.sleep(time*1000);
            JsonPath response = RestAssured
                    .given()
                    .get("https://playground.learnqa.ru/ajax/api/longtime_job?token=" + token)
                    .jsonPath();

            String status = response.get("status");

            Assert.assertEquals("Job is NOT ready", status);
            System.out.println("Status: " + status);

        }
    }

    public void submitTask() {
            JsonPath response = RestAssured
                    .given()
                    .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                    .jsonPath();
            time = response.get("seconds");
            token = response.get("token");
        }

    }


