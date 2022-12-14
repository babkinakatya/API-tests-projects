package Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lib.BaseTestCase;
import lib.UserAgentArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.HashMap;
import java.util.Map;

public class Ex13 extends BaseTestCase {

    @ParameterizedTest
    @ArgumentsSource(UserAgentArgumentsProvider.class)
    public void validateUserAgent (String userAgent, String expectedPlatform, String expectedBrowser, String expectedDevice){

        RequestSpecification spec  = RestAssured.given();
        spec.baseUri("https://playground.learnqa.ru/ajax/api/user_agent_check");

        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", userAgent);

        Response responseForCheck = spec.headers(headers).get().andReturn();
        String actualPlatform = getStringFromJson(responseForCheck,"platform");
        String actualBrowser = getStringFromJson(responseForCheck,"browser");
        String actualDevice = getStringFromJson(responseForCheck,"device");

        if (!actualPlatform.equals(expectedPlatform)){
            System.out.println(userAgent);
            System.out.println("Неправильный параметр Platform = " + actualPlatform);
        } else if (!actualBrowser.equals(expectedBrowser)) {
            System.out.println(userAgent);
            System.out.println("Неправильный параметр Browser = " + actualBrowser);
        } else if (!actualDevice.equals(expectedDevice)){
            System.out.println(userAgent);
            System.out.println("Неправильный параметр Device = " + actualDevice);
        }
    }
}
