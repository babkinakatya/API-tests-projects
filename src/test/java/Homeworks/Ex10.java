package Homeworks;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Ex10 {

    @Test
    public void testValidateTextWithSpaces(String text){
        Assert.assertTrue("Text is more than 15 chars with spaces", text.length()<=15);
    }

    @Test
    public void testValidateTextWithoutSpaces(String text){
        Assert.assertTrue("Text is more than 15 chars without spaces", text.replace(" ", "").length()<=15);
    }
}
