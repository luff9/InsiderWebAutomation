package lib;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AssertLib {
    AssertLib (){}
    public static void equals(WebDriver driver, String actual, String expected) {
        if (!actual.equals(expected)) {
            DriverLib.takeScreenShot(driver);
        }
        Assert.assertEquals(actual, expected);
    }

    public static void equals(WebDriver driver, boolean actual, boolean expected) {
        if (actual != expected) {
            DriverLib.takeScreenShot(driver);
        }
        Assert.assertEquals(actual, expected);
    }
}
