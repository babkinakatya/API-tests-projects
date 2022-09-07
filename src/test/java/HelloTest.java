import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloTest {

    @Test
    public void testHello(){
        String name = "Katya";
        System.out.println("Hello from "+ name);
    }

    @Test
    public void testSendGetRequest(){
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();
        System.out.println(response.body().asString());
    }
}
