import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Lessons {

    @Test
    public void testRestAssured(){
        Map<String, String> params = new HashMap<>();
        params.put("name","Katya");
        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();
        String answer = response.get("answer");
        if (answer == null){
            System.out.println("The key answer2 is absent");
        } else{
            System.out.println(answer);
        }
    }

    @Test
    public void testGetType(){
        Response response = RestAssured
                .given()
                .queryParam("param1","value1")
                .queryParam("param2","value2")
                .get("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response.prettyPrint();
    }

    @Test
    public void testPostType(){
        Map<String, Object> body = new HashMap<>();
        body.put("param1","value1");
        body.put("param2","value2");
        Response response = RestAssured
                .given()
                .body(body)
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();
        response.print();
    }

    @Test
    public void testPrintGetStatusCode(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_500")
                .andReturn();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }

    @Test
    public void testRedirect303(){
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }

    @Test
    public void testGetHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put("myHeader1","myValue1");
        headers.put("myHeader2","myValue2");
        Response response = RestAssured
                .given()
                .headers(headers)
                .when()
                .get("https://playground.learnqa.ru/api/show_all_headers")
                .andReturn();
        response.prettyPrint();

        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);
    }

    @Test
    public void testGetLocationHeader(){
        Map<String, String> headers = new HashMap<>();
        headers.put("myHeader1","myValue1");
        headers.put("myHeader2","myValue2");
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();
        response.prettyPrint();

        String locationHeader = response.getHeader("Location");
        System.out.println(locationHeader);
    }

    @Test
    public void testGetCookie(){
        Map<String, String> data = new HashMap<>();
        data.put("login","secret_login");
        data.put("password","secret_pass");
        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

        System.out.println("\nPretty text:");
        response.prettyPrint();

        System.out.println("\nHeaders:");
        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);

        System.out.println("\nCookies:");
        Map <String, String> responseCookies = response.getCookies();
        System.out.println(responseCookies);
    }

    @Test
    public void testGetAndPutCookie(){
        Map<String, String> data = new HashMap<>();
        data.put("login","secret_login");
        data.put("password","secret_pass");

        Response responseForGet = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

        String responseCookie = responseForGet.getCookie("auth_cookie");

        Map<String, String> cookies = new HashMap<>();
        if (responseCookie != null) {
            cookies.put("auth_cookie", responseCookie);
        }

        Response responseForCheck = RestAssured
                .given()
                .body(data)
                .cookies(cookies)
                .when()
                .post("https://playground.learnqa.ru/api/check_auth_cookie")
                .andReturn();

        responseForCheck.print();
    }
}
